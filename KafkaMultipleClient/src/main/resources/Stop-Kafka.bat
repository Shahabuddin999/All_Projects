@echo off
echo ============================================
echo Stopping Kafka and Zookeeper Services
echo ============================================

:: Set Kafka installation directory
set KAFKA_HOME=D:\kafka_2.13-3.8.1

:: Stop Kafka server
echo.
echo Stopping Kafka Server...
start "" /B "%KAFKA_HOME%\bin\windows\kafka-server-stop.bat"
timeout /t 3 >nul

:: Stop Zookeeper
echo.
echo Stopping Zookeeper...
start "" /B "%KAFKA_HOME%\bin\windows\zookeeper-server-stop.bat"
timeout /t 3 >nul

echo.
echo All services stop script triggered.
pause
