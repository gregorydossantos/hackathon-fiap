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

    private ResponseEntity<Void> voidResponse;
    private ResponseEntity<UserResponse> userResponse;
    private ResponseEntity<UserResponse[]> userResponseList;

    @Given("i do an request GET in the resource users")
    public void iDoAnRequestGETInTheResourceUsers() {
        System.out.println("### USERS-API LAYER CONTROLLER - Get all Users - ###");
        userResponseList = testRestTemplate.exchange(PATH_USERS, HttpMethod.GET, null, UserResponse[].class);
    }

    @Then("response from GET must be status code {int}")
    public void responseFromGETMustBeStatusCode(int statusCode) {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 200 ###");
        assertThat(userResponseList.getStatusCode().value()).isEqualTo(statusCode);
    }

    @And("the body must contain a list of users")
    public void theBodyMustContainAListOfUsers() {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate list of users ###");
        assertThat(userResponseList.getBody()).isNotNull();
    }

    @Given("i send a POST request with name email password exchange successfully")
    public void iSendAPOSTRequestWithNameEmailPasswordExchangeSuccessfully() {
        String json = """
                {
                    "name":"Lucca",
                    "email": "uca@test.com",
                    "password": "uca-pass",
                    "exchange": "Mail"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        System.out.println("### USERS-API LAYER CONTROLLER - Create a user: " + json);
        voidResponse = testRestTemplate.exchange(PATH_USERS, HttpMethod.POST, entity, Void.class);
    }

    @Then("response from POST must be status code {int}")
    public void responseFromPOSTMustBeStatusCode(int statusCode) {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 201 ###");
        assertThat(voidResponse.getStatusCode().value()).isEqualTo(statusCode);
    }

    @Given("i send a PATCH request with fields that i wanna change successfully {int}")
    public void iSendAPATCHRequestWithFieldsThatIWannaChangeSuccessfully(int id) {
        String json = """
                {
                    "name":"Eliza",
                    "email": "eliza@test.com",
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        System.out.println("### USERS-API LAYER CONTROLLER - Update a user: " + json);
        var pathResource = PATH_USERS + "/" + id;
        userResponse = testRestTemplate.exchange(pathResource, HttpMethod.PATCH, entity, UserResponse.class);
    }

    @Then("response from PATCH must be status code {int}")
    public void responseFromPATCHMustBeStatusCode(int statusCode) {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 200 ###");
        assertThat(userResponse.getStatusCode().value()).isEqualTo(statusCode);
    }

    @And("the body must contain updated user")
    public void theBodyMustContainUpdatedUser() {
        System.out.println("### USERS-API LAYER CONTROLLER - Update user ###");
        assertThat(userResponse.getBody()).isNotNull();
    }

    @Given("i do an request DELETE passing the id user {int}")
    public void iDoAnRequestDELETEPassingTheIdUser(int id) {
        System.out.println("### USERS-API LAYER CONTROLLER - Delete user ###");

        var pathResource = PATH_USERS + "/" + id;
        voidResponse = testRestTemplate.exchange(pathResource, HttpMethod.DELETE, null, Void.class);
    }

    @And("user was deleted from database {long}")
    public void userWasDeletedFromDatabase(long idUser) {
        System.out.println("### USERS-API LAYER REPOSITORY - Check if user was deleted from database ###");
        assertThat(userRepository.findById(idUser).isEmpty()).isTrue();
    }

    @Then("response from DELETE must be status code {int}")
    public void responseFromDELETEMustBeStatusCode(int statusCode) {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 200 ###");
        assertThat(voidResponse.getStatusCode().value()).isEqualTo(statusCode);
    }
}
