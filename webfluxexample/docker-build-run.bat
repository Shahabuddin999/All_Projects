@echo off
echo Building Spring Boot project with Maven...

cd /d D:\OLX-Apps\OLX-Updated.zip\webfluxexample

call mvn clean install -DskipTests

echo Building Docker image...
docker build -t webfluxexample .

# If you uncomment below 2 lines then it will push you just created docker images(webfluxexample) to DockerHub.
#docker tag webfluxexample shahabuddin999/webfluxexample:latest 
#docker push shahabuddin999/webfluxexample:latest

echo Running Docker container...
docker run -p 8080:8080 webfluxexample
