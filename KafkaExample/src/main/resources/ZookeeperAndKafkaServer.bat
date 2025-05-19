@echo off 

REM Set Kafka installation path
set KAFKA_HOME=C:\kafka_2.13-3.8.1

del /s /q C:\tmp\zookeeper
del /s /q C:\tmp\kafka-logs

echo Starting Zookeeper...
start "Zookeeper" cmd /k "cd /d %KAFKA_HOME%\bin\windows && zookeeper-server-start.bat ..\..\config\zookeeper.properties"

REM Wait for Zookeeper to fully start
timeout /t 5 >nul

echo Starting Kafka Broker...
start "Kafka" cmd /k "cd /d %KAFKA_HOME%\bin\windows && kafka-server-start.bat ..\..\config\server.properties"

echo Both Zookeeper and Kafka are starting in separate windows.
