# Start from a lightweight Java runtime
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar to container
COPY target/*.jar app.jar

# Expose port for Kubernetes or Docker
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
