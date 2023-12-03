# Use a base image with JDK and Maven installed
FROM adoptopenjdk:11-jre-hotspot AS builder
WORKDIR /app

# Copy the Maven wrapper files and the pom.xml to the container
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download the Maven dependencies (cache the dependencies if unchanged)
RUN ./mvnw dependency:go-offline

# Copy the application source code to the container
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Use a lighter base image for runtime
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=builder /app/target/STC-ASSESMENT.jar STC-ASSESMENT.jar

# Specify the command to run your application
CMD ["java", "-jar", "STC-ASSESMENT.jar"]