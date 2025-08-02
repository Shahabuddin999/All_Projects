@echo off
echo Building Spring Boot project with Maven...

cd /d D:\OLX-Apps\OLX-Updated\BootWithDatabase

call mvn clean install -DskipTests

echo Building Docker image from docker-compose...
docker-compose build

echo Stopping and removing existing containers...
docker-compose down -v

echo Starting application stack...
docker-compose up
