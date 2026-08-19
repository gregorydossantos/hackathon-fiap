# Exchange Games API
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gregorydossantos/projeto-sds3/blob/main/LICENSE)
<br/>This is a project from my postgraduate in Architecture and Software Development at FIAP.

## About project
This project consists of creating a monolith with one module. Will be built on the Rest API standard and also the
main libraries used on the market today. I choose use the DDD pattern for structure my code.

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
- SonarQube
- Redis (I had to comment this implementation, because I couldn't upload redis service at PROD environment)
- Cucumber 7.31.0

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

#### Jacoco Report:
To run and generate a report about coverage tests, run mvn test and after complete you can look at in: /target/site
/jacoco/index.html.
<br/> Jacoco Report Example:
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/jacoco-report.png)

### SonarQube:
To build and uses report from Jacoco for generate quality and coverage tests of the API, run this commands:
<br/> To up SonarQube container at local, navigate to the folder /docker
<br/> - sudo docker compose up -d
<br/> Generate for SonarQube the report from Jacoco
<br/> - mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=squ_a683a0d54b71d646765d8dbc8a92652bf971ec7a
![Web 1](https://github.com/gregorydossantos/hackathon-fiap/blob/develop/assets/sonar-qube.png)

### Documentation (Swagger - Endpoints):
After running locally the project, we can access the API documentation through Swagger: 
<br/> Link: http://localhost:8080/swagger-ui/index.html#/

### Deploy API:
I use RENDER to deploy app.

| Custom Domain                           | Base Path | Resources  |
|-----------------------------------------|-----------|------------|
| https://hackathon-fiap-api.onrender.com | /api/v1   | /users     |
| https://hackathon-fiap-api.onrender.com | /api/v1   | /games     |
| https://hackathon-fiap-api.onrender.com | /api/v1   | /exchanges |

#### [POST] Create user
- Payload request
```json
{
  "name":"Test",
  "email": "test@test.com",
  "password": "code",
  "exchange": "Mail"
}
```
- Payload response
```json
{
  "statusCode": "201 - Created"
}
```

#### [GET] List all users
- Payload response
```json
[
  {
    "name": "Test",
    "email": "test@test.com",
    "exchange": "Mail"
  }
]
```

#### [POST] Update user
- Payload request
```json
{
  "name":"Test update",
  "email": "test@test.com",
  "password": "code",
  "exchange": "In person"
}
```
- Payload response
```json
{
  "name":"Test update",
  "email": "test@test.com",
  "exchange": "In person"
}
```

#### [DELETE] Delete user
- Payload response
```json
{
  "statusCode": "200 - Success"
}
```

#### [POST] Create game
- Payload request
```json
{
  "name":"Name of game",
  "brand": "Company name",
  "user_id": 1
}
```
- Payload response
```json
{
  "statusCode": "201 - Created"
}
```

#### [GET] List all games
- Payload response
```json
[
  {
    "name":"Name of game",
    "brand": "Company name",
    "user_id": 1
  }
]
```

#### [DELETE] Delete game
- Payload response
```json
{
  "statusCode": "200 - Success"
}
```

#### [POST] Send message
-Payload request
```json
{
  "user_id": 1,
  "game_id": 1
}
```
- Payload response
```json
{
  "statusCode": "201 - Created"
}
```

#### Important Notes:
To run locally the API using the DEV environment, first we have to confirm that docker already install in your machine, after that
follow these steps:
<br/> - Navigate to /hackathon-fiap/docker
<br/> - Run the command: sudo docker compose up -d
<br/> - To stop container: sudo docker compose down
<br/> I also included a directory containing the endpoint call collections in the /collections folder
<br/> If we want use Redis, you need uncomment lines at these files:
- docker-compose.yml
- applications.yml (all environments)
- HackathonApplication.java
- UserUseCaseQueryImpl.java
<br/> To have access in H2 console (web) you need to uncomment lines at these file:
- WebSecurityConfig.java
<br/> You can run integration tests using DEV profile, but first you need include some users using the resource from API Users 
<br/> To include the games data you just need copy/paste from scripts-db folder the commands line to insert data on database 
<br/> After that you can running integration tests using Cucumber in the application environment 