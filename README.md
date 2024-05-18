# Exchange Games API
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gregorydossantos/projeto-sds3/blob/main/LICENSE)
<br/>This is a project from my pos graduate studies in architecture and software development at FIAP.

## About project
This project consists of creating a monolith with one module. Will be built on the Rest API standard and also the
main libraries used on the market today.

## Technologies and Libraries:
- Java 17
- Spring-boot 3.2.2
- Spring Security
- Database H2
- Postgres
- Docker
- Lombok
- JUnit
- Mockito
- Jacoco Report 0.8.8
- Mapstruct 1.5.5.Final
- OpenAPI 2.0.4

### API Documentation
#### Architecture Draw
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/architecture-draw.png)
<br />
#### Database Model
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/db-model.png)
<br />
#### Use Cases
In progress...
<br />
#### Notes:
To create the database and upload it locally, first confirm that you have docker installed on your machine, after that
follow these steps:
<br/> - cd hackathon/docker
<br/> - sudo docker-compose up -d (to start container with database)
<br/> - sudo docker-compose down -d (to stop container)
<br/> I also included a directory containing the endpoint call collections in the postman-collections folder

### Documentation (Swagger - Endpoints):
After running the project, we can access the API documentation through Swagger: <br/>
Link: http://localhost:8080/swagger-ui/index.html#/