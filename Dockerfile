#
# BUILD STAGE
#
FROM gradle:8.3.0-jdk17 AS build
COPY . .
RUN gradle clean bootJar --no-daemon

#
# PACKAGE STAGE
#
FROM amazoncorretto:17
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]