FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim
EXPOSE 8000
COPY --from=build /target/hackathon-1.jar hackathon.jar

ENTRYPOINT ["java", "-jar", "hackathon.jar"]