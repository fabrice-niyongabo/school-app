FROM maven:latest AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml /app/

# Copy the source code
COPY src /app/src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:21-jdk

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage (the name comes from pom.xml)
COPY --from=build /app/target/students-backend-app.jar /app/students-backend-app.jar

# Expose the port the app runs on
EXPOSE 5001

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/students-backend-app.jar"]
