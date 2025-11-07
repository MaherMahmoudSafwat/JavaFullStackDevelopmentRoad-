# Use OpenJDK 26 as the base image for our Java application
# This provides a complete Java runtime environment
FROM openjdk:26-ea-trixie

# Add our packaged JAR file from the target directory to the container
# The first path is the source file on our computer
# The second path is the destination inside the container
ADD target/DockerDemo.jar Docker-Demo.jar

# EXPOSE 8090 - This line is MISSING but should be added!
# EXPOSE tells Docker that the container listens on port 8090 at runtime
# This is for documentation and doesn't actually publish the port

# Define the command to run when the container starts
# This tells Docker to execute our Spring Boot application
ENTRYPOINT ["java", "-jar", "/Docker-Demo.jar"]


