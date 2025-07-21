@echo off
echo Building Spring Boot project with Maven...

cd /d D:\OLX-Apps\OLX-Updated\BootWithDatabase

call mvn clean install -DskipTests

# echo Building Docker image...
# docker build -t bootwithdatabase . # its not required if you are using docker-compose up --build because it create image then run db then run image

# If you uncomment below 2 lines then it will push you just created docker images(bootwithdatabase) to DockerHub.
# docker tag bootwithdatabase shahabuddin999/bootwithdatabase:latest 
# docker push shahabuddin999/bootwithdatabase:latest

echo Running Docker container...
# this below line preventing not to use older image if image is in cache so it will use latest image
docker build --no-cache -t bootwithdatabase . 

docker-compose down -v 
docker-compose up --build

# docker run -p 8083:8083 bootwithdatabase
