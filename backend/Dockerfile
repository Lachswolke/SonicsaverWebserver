# syntax=docker/dockerfile:1.4

# Build Stage
FROM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

# Runtime Stage
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

EXPOSE 8080
COPY --from=builder /app/target/*.jar app.jar

# Set JVM options if needed and run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
