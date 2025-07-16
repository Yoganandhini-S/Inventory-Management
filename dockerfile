# Use base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR into container
COPY target/ROOT.war app.war
COPY src/main/Webapp /app/src/main/Webapp

# Expose port (same as Spring Boot port)
EXPOSE 8087

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.war"]

