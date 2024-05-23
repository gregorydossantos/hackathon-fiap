FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./mvnw bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8000
COPY --from=build /build/libs/hackathon-1.jar app.jar

ENTRYPOINT["java", "-jar", "app.jar"]