@echo off
echo -------------------------------------------
echo 🚀 Starting fresh Spring Boot Kubernetes App
echo -------------------------------------------

REM 🔄 Delete old Minikube cluster (if exists)
echo 🔥 Deleting old minikube cluster...
minikube delete

REM 🐳 Start Minikube with Docker driver
echo 🚀 Starting Minikube with Docker driver...
minikube start --driver=docker

REM 🐋 Set Docker environment to Minikube
echo 🔧 Setting Docker env to Minikube...
for /f "delims=" %%i in ('minikube docker-env --shell cmd ^| findstr /v "REM"') do call %%i

REM 🧼 Delete old Kubernetes objects if they exist
echo 🔄 Cleaning old Kubernetes deployments/services...
kubectl delete deployment springboot-k8s-demo --ignore-not-found
kubectl delete service springboot-service --ignore-not-found
kubectl delete deployment postgres-deployment --ignore-not-found
kubectl delete service postgres-service --ignore-not-found

REM 🛠️ Build Docker image inside Minikube Docker
echo 🛠️ Building Docker image: bootwithdatabase...
docker build -t bootwithdatabase .

REM 📦 Apply Kubernetes manifests
echo 📂 Applying Kubernetes configs...
kubectl apply -f k8s/

REM 🌐 Expose Spring Boot service (NodePort)
echo 🌍 Exposing service as NodePort...
kubectl expose deployment springboot-k8s-demo --type=NodePort --port=8080 --name=springboot-service

REM 🧪 Show services
echo 📡 Current Services:
kubectl get svc

REM 🌐 Open service URL in new terminal
echo 🔗 Opening app in browser...
start cmd /k "minikube service springboot-service"

echo ✅ Done! App is running.
