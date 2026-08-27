package com.fiap.gregory.hackathon.integration.steps;

import com.fiap.gregory.hackathon.infra.db.model.Users;
import com.fiap.gregory.hackathon.infra.db.repository.IUserRepository;
import com.fiap.gregory.hackathon.rest.dto.response.UserResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static com.fiap.gregory.hackathon.rest.path.Routes.PATH_USERS;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerQueryStep {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IUserRepository userRepository;

    private ResponseEntity<Void> voidResponse;
    private ResponseEntity<UserResponse> userResponse;
    private ResponseEntity<UserResponse[]> userResponseList;
    private Users user;

    @BeforeAll
    void setUp() {
        userRepository.deleteAll();
    }

    @Given("i send a POST request with name email password exchange successfully")
    public void iSendAPOSTRequestWithNameEmailPasswordExchangeSuccessfully() {
        String json = """
                {
                    "name":"Test",
                    "email": "test@test.com",
                    "password": "test-pass",
                    "exchange": "Mail"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        System.out.println("### USERS-API LAYER CONTROLLER - Create a user: " + json);
        voidResponse = testRestTemplate.exchange(PATH_USERS, HttpMethod.POST, entity, Void.class);
    }

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

    @Then("response from POST must be status code {int}")
    public void responseFromPOSTMustBeStatusCode(int statusCode) {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 201 ###");
        assertThat(voidResponse.getStatusCode().value()).isEqualTo(statusCode);
    }

    @Given("i send a PATCH request with fields that i wanna change successfully")
    public void iSendAPATCHRequestWithFieldsThatIWannaChangeSuccessfully() {
        user = userRepository.findAll().stream().findFirst().get();
        String json = """
                {
                    "name":"Gregory",
                    "email": "greg@test.com",
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        System.out.println("### USERS-API LAYER CONTROLLER - Update a user: " + json);
        var pathResource = PATH_USERS + "/" + user.getId().toString();
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

    @Given("i do an request DELETE passing the id user")
    public void iDoAnRequestDELETEPassingTheIdUser() {
        System.out.println("### USERS-API LAYER CONTROLLER - Delete user ###");

        var pathResource = PATH_USERS + "/" + user.getId().toString();
        voidResponse = testRestTemplate.exchange(pathResource, HttpMethod.DELETE, null, Void.class);
    }

    @And("user was deleted from database")
    public void userWasDeletedFromDatabase() {
        System.out.println("### USERS-API LAYER REPOSITORY - Check if user was deleted from database ###");
        assertThat(userRepository.findById(user.getId()).isEmpty()).isTrue();
    }

    @Then("response from DELETE must be status code {int}")
    public void responseFromDELETEMustBeStatusCode(int statusCode) {
        System.out.println("### USERS-API LAYER CONTROLLER - Validate response status code equals 200 ###");
        assertThat(voidResponse.getStatusCode().value()).isEqualTo(statusCode);
    }
}
