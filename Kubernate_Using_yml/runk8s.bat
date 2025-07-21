@echo off
setlocal

:: ==== Generate unique image tag in format dd-MM-yyyy_HH-mm-ss-AM ====
for /f %%i in ('powershell -Command "$d = Get-Date -Format \"dd-MM-yyyy_HH-mm-ss tt\"; $d -replace \":\", \"-\" -replace \" \", \"-\""' ) do set TAG=%%i

echo ==== TAG generated: %TAG%

::echo Deleting Minikube...
::call minikube delete

::echo Starting Minikube...
::call minikube start --driver=docker

echo ==== Maven Build ====
call mvn clean package

echo ==== Docker Build ====
call docker build -t shahabuddin999/docker-image-spring:%TAG% .

echo ==== Docker Push ====
call docker push shahabuddin999/docker-image-spring:%TAG%

echo ==== Apply Deployment (base) ====
call kubectl apply -f deployment.yml

echo ==== Update Deployment Image ====
call kubectl set image deployment/kubernate-using-yml-file-deployment kubernate-using-yml-file-container=shahabuddin999/docker-image-spring:%TAG%

echo ==== Rollout Status ====
call kubectl rollout status deployment/kubernate-using-yml-file-deployment

echo ==== Apply Service ====
call kubectl apply -f service.yml

echo ==== Get Pods ====
call kubectl get pods

echo ==== Port Forward ====
kubectl port-forward deployment/kubernate-using-yml-file-deployment 8080:8080

endlocal
