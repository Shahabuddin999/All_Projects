# Use a minimal base image
FROM openjdk:17-jdk-slim AS builder

# Create app directory
WORKDIR /app

# Copy the JAR into the image
COPY target/*.jar app.jar

# Expose port 8080 to the outside
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
