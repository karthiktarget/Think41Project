FROM ubuntu:latest
LABEL authors="Karthik"

ENTRYPOINT ["top", "-b"]
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
