# Exchange Games API
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gregorydossantos/projeto-sds3/blob/main/LICENSE)
<br/>This is a project from my pos graduate studies in architecture and software development at FIAP.

## About project
This project consists of creating a monolith with one module. Will be built on the Rest API standard and also the
main libraries used on the market today.

## Technologies and Libraries:
- Java 17
- Maven 3.8.1
- Rest-assured 5.4.0
- JMS 6.1.7
- Artemis 3.2.5
- Artemis JMS Server 2.33.0
- Jacoco Report 0.8.8
- Mapstruct 1.5.5.Final
- OpenAPI 2.0.4
- Spring Security
- Database H2
- Postgres
- Docker
- Lombok
- JUnit
- Mockito

### API Documentation
#### Architecture Draw
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/architecture-draw.png)
<br />
#### Database Model
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/db-model.png)
<br />
#### Sequences Diagram
##### Users Endpoints
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/users-sequence.png)
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/users-sequence-1.png)
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/users-sequence-2.png)

##### Game Endpoint
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/games-sequence.png)

##### Exchange Endpoint
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/exchanges-sequence.png)

#### Notes:
To create the database and upload it locally, first confirm that you have docker installed on your machine, after that
follow these steps:
<br/> - cd hackathon/docker
<br/> - sudo docker-compose up -d (to start container with database)
<br/> - sudo docker-compose down -d (to stop container)
<br/> I also included a directory containing the endpoint call collections in the postman-collections folder

#### Jacoco Report:
To run and generate a report about coverage tests, run mvn test and after complete you can look at in: /target/site
/jacoco/index.html.
<br/> Jacoco Report Example:
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/jacoco-report.png)


### Documentation (Swagger - Endpoints):
After running the project, we can access the API documentation through Swagger: <br/>
Link: http://localhost:8080/swagger-ui/index.html#/

### Deploy da API:
URL Render: https://hackathon-fiap-api.onrender.com