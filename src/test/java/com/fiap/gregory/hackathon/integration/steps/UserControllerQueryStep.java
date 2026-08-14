package com.fiap.gregory.hackathon.integration.steps;

import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_USERS;
import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerQueryStep {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IUserRepository userRepository;

    private ResponseEntity<Void> createResponse;
    private ResponseEntity<UserResponse[]> getUserResponse;

    @Given("i send a POST request with name email password exchange successfully")
    public void iSendAPOSTRequestWithNameEmailPasswordExchangeSuccessfully() {
        String json = """
                {
                    "name":"Gregory",
                    "email": "gregory@test.com",
                    "password": 44444444,
                    "exchange": "Mail"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        System.out.println("### LAYER CONTROLLER - Create a user: " + json);
        createResponse = testRestTemplate.exchange(PATH_USERS, HttpMethod.POST, entity, Void.class);
    }

    @And("response must be http status code {int}")
    public void responseMustBeHttpStatusCode(int statusCode) {
        System.out.println("### LAYER CONTROLLER - Validate response status code equals 201 ###");
        assertThat(createResponse.getStatusCode().value()).isEqualTo(statusCode);
    }

    @When("i do an request GET")
    public void iDoAnRequestGET() {
        System.out.println("### LAYER CONTROLLER - Query users ###");
        getUserResponse = testRestTemplate.exchange(PATH_USERS, HttpMethod.GET, null,
                UserResponse[].class);

        System.out.println("### LAYER CONTROLLER - Response: " + getUserResponse);
    }

    @Then("response must be status code {int}")
    public void responseMustBeStatus(int statusCode) {
        System.out.println("### LAYER CONTROLLER - Validate response status code equals 200 ###");
        assertThat(getUserResponse.getStatusCode().value()).isEqualTo(statusCode);
    }

    @And("the body must contain a list of users")
    public void theBodyMustContainAListOfUsers() {
        System.out.println("### LAYER CONTROLLER - Validate list of users ###");
        assertThat(getUserResponse.getBody()).isNotNull();
    }
}
