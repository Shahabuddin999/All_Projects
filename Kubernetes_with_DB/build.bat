@echo off
setlocal enabledelayedexpansion

REM === Configuration ===
set DOCKER_USERNAME=shahabuddin999
set IMAGE_NAME=kubernetesdb-image-spring
set FULL_IMAGE=%DOCKER_USERNAME%/%IMAGE_NAME%

echo === STEP 1: Clean previous Kubernetes resources ===
kubectl delete all --all

echo === STEP 2: Stop and delete Minikube (clean start) ===
minikube stop
minikube delete

echo === STEP 3: Build Spring Boot project ===
call mvn clean package -DskipTests
IF %ERRORLEVEL% NEQ 0 (
    echo ❌ Maven build failed. Exiting...
    exit /b 1
)

echo === STEP 4: Build Docker image ===
docker build -t %FULL_IMAGE% .
IF %ERRORLEVEL% NEQ 0 (
    echo ❌ Docker image build failed. Exiting...
    exit /b 1
)

echo === STEP 5: Push Docker image to Docker Hub ===
docker push %FULL_IMAGE%
IF %ERRORLEVEL% NEQ 0 (
    echo ❌ Docker image push failed. Ensure you're logged in to Docker Hub.
    exit /b 1
)

echo === STEP 6: Start Minikube ===
minikube start --driver=docker
IF %ERRORLEVEL% NEQ 0 (
    echo ❌ Minikube failed to start. Exiting...
    exit /b 1
)

echo === STEP 7: Apply Kubernetes YAML files ===
kubectl apply -f postgres-configmap.yaml
kubectl apply -f postgres-deployment.yaml
kubectl apply -f postgres-service.yaml
kubectl apply -f springboot-deployment.yaml
kubectl apply -f springboot-service.yaml

REM === STEP 8: Wait for Spring Boot pod to be Running ===
echo.
echo === Waiting for Spring Boot pod to be Ready ===
:check_pod
kubectl get pods -l app=springboot-app --no-headers > tmp.txt
set /p line=<tmp.txt
for /f "tokens=3" %%s in ("!line!") do set podStatus=%%s
del tmp.txt

echo Current pod status: !podStatus!
if /I NOT "!podStatus!"=="Running" (
    echo Waiting for pod to be Running...
    timeout /t 5 >nul
    goto check_pod
)

echo ✅ Spring Boot pod is running. Waiting for app to initialize...
timeout /t 30 >nul

echo === STEP 9: Port Forward to localhost:8080 ===
start cmd /k "kubectl port-forward svc/springboot-service 8080:8080"

echo === STEP 10: Opening app in browser ===
start http://localhost:8080

pause
