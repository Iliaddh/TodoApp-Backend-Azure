# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set working directory
WORKDIR /app

# Copy the jar file into the container
COPY build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]