package com.fiap.gregory.hackathon.integration.steps;

import com.fiap.gregory.hackathon.rest.dto.response.GameResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_USERS;
import static org.assertj.core.api.Assertions.assertThat;

public class GameControllerQueryStep {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private ResponseEntity<GameResponse[]> response;

    @Given("i do an request GET in the resource games")
    public void iDoAnRequestGETInTheResourceGames() {
        System.out.println("### GAME-API LAYER CONTROLLER - Get all Users - ###");
        response = testRestTemplate.exchange(PATH_USERS, HttpMethod.GET, null, GameResponse[].class);
    }

    @Then("games response must be status code {int}")
    public void gamesResponseMustBeStatus(int statusCode) {
        System.out.println("### GAME-API LAYER CONTROLLER - Validate response status code equals 200 ###");
        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    @And("the body must contain a list of games")
    public void theBodyMustContainAListOfGames() {
        System.out.println("### GAME-API LAYER CONTROLLER - Validate list of users ###");
        assertThat(response.getBody()).isNotNull();
    }
}
