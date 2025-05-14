# Use a lightweight OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Define the build argument for the JAR file location
ARG JAR_FILE=target/*.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
