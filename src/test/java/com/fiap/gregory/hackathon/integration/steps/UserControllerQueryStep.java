package com.fiap.gregory.hackathon.integration.steps;

import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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
    private ResponseEntity<UserResponse[]> response;

    @Given("i do an request GET in the resource users")
    public void iDoAnRequestGETInTheResourceUsers() {
        System.out.println("### USERS-API LAYER CONTROLLER - Get all Users - ###");
        response = testRestTemplate.exchange(PATH_USERS, HttpMethod.GET, null, UserResponse[].class);
    }

    @Then("users response must be status code {int}")
    public void usersResponseMustBeStatus(int statusCode) {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 200 ###");
        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    @And("the body must contain a list of users")
    public void theBodyMustContainAListOfUsers() {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate list of users ###");
        assertThat(response.getBody()).isNotNull();
    }

    @Given("i send a POST request with name email password exchange successfully")
    public void iSendAPOSTRequestWithNameEmailPasswordExchangeSuccessfully() {
        String json = """
                {
                    "name":"Test",
                    "email": "test@test.com",
                    "password": "test-password",
                    "exchange": "In person"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        System.out.println("### USERS-API LAYER CONTROLLER - Create a user: " + json);
        createResponse = testRestTemplate.exchange(PATH_USERS, HttpMethod.POST, entity, Void.class);
    }

    @Then("return must be status code {int}")
    public void returnMustBeStatusCode(int statusCode) {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 201 ###");
        assertThat(createResponse.getStatusCode().value()).isEqualTo(statusCode);
    }

//    @Given("i send a POST request with name email password exchange successfully")
//    public void iSendAPOSTRequestWithNameEmailPasswordExchangeSuccessfully() {
//        String json = """
//                {
//                    "name":"Test",
//                    "email": "test@test.com",
//                    "password": "test-password",
//                    "exchange": "In person"
//                }
//                """;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(json, headers);
//
//        System.out.println("### USERS-API LAYER CONTROLLER - Create a user: " + json);
//        createResponse = testRestTemplate.exchange(PATH_USERS, HttpMethod.POST, entity, Void.class);
//    }
//
//    @Then("return must be status code {int}")
//    public void returnMustBeStatusCode(int statusCode) {
//        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 201 ###");
//        assertThat(createResponse.getStatusCode().value()).isEqualTo(statusCode);
//    }
}
