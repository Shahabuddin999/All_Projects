@echo off
setlocal

:: ==== Generate unique image tag in format dd-MM-yyyy_HH-mm-ss-AM ====
for /f %%i in ('powershell -Command "$d = Get-Date -Format \"dd-MM-yyyy_hh-mm-ss tt\"; $d -replace \":\", \"-\" -replace \" \", \"-\""' ) do set TAG=%%i
echo ==== TAG generated: %TAG%

:: ==== Set Docker as Minikube driver if not already set ====
echo Checking Minikube driver...
for /f %%d in ('minikube config get driver 2^>nul') do set DRIVER=%%d

if not defined DRIVER (
    echo No Minikube driver set. Setting to 'docker'...
    call minikube config set driver docker
)

:: ==== Check if Minikube is running ====
echo Checking if Minikube is running...
minikube status | findstr /C:"host: Running" >nul
if %errorlevel% neq 0 (
    echo ⚠ Kubernetes is not running. Starting Minikube...
    call minikube start
    if %errorlevel% neq 0 (
        echo ❌ Failed to start Minikube. Exiting...
        exit /b 1
    )
) else (
    echo ✅ Minikube is already running.
)

:: ==== Maven Build ====
echo ==== Maven Build ====
call mvn clean package -DskipTests
if %errorlevel% neq 0 (
    echo ❌ Maven build failed. Exiting...
    exit /b 1
)

:: ==== Docker Build ====
echo ==== Docker Build ====
call docker build -t shahabuddin999/docker-image-spring:%TAG% .
if %errorlevel% neq 0 (
    echo ❌ Docker build failed. Exiting...
    exit /b 1
)

:: ==== Docker Push ====
echo ==== Docker Push ====
call docker push shahabuddin999/docker-image-spring:%TAG%
if %errorlevel% neq 0 (
    echo ❌ Docker push failed. Exiting...
    exit /b 1
)

:: ==== Apply Kubernetes Deployment YAML ====
echo ==== Apply Deployment YAML ====
call kubectl apply -f deployment.yml

:: ==== Update Deployment Image ====
echo ==== Update Deployment Image ====
call kubectl set image deployment/kubernate-using-yml-file-deployment kubernate-using-yml-file-container=shahabuddin999/docker-image-spring:%TAG%

:: ==== Rollout Status ====
echo ==== Rollout Status ====
call kubectl rollout status deployment/kubernate-using-yml-file-deployment

:: ==== Apply Kubernetes Service YAML ====
echo ==== Apply Service YAML ====
call kubectl apply -f service.yml

:: ==== Show Running Pods ====
echo ==== Get Pods ====
call kubectl get pods

:: ==== Port Forward to localhost:8080 ====

echo ==== ✅ Deployment complete. App should be available at http://localhost:8080 ====

echo ==== Port Forward to localhost:8080 ====
kubectl port-forward deployment/kubernate-using-yml-file-deployment 8080:8080

endlocal
