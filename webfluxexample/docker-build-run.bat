@echo off
echo Building Spring Boot project with Maven...

cd /d D:\OLX-Apps\OLX-Updated.zip\webfluxexample

call mvn clean install -DskipTests

echo Building Docker image...
docker build -t webfluxexample .

echo Running Docker container...
docker run -p 8080:8080 webfluxexample
