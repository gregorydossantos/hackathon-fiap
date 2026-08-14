package com.fiap.gregory.hackathon.integration.steps;

import com.fiap.gregory.hackathon.infra.db.model.Users;
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

    private ResponseEntity<UserResponse[]> response;

    @Given("that i have registered users in my database")
    public void thatIHaveRegisteredUsersInMyDatabase() {
        System.out.println("### LAYER DATABASE - Create a user for the test ###");
        var user = Users.builder()
                .name("Gregory")
                .email("greg@email.com")
                .password("11111111")
                .exchange("Mail")
                .build();
        userRepository.saveAndFlush(user);

        System.out.println("### LAYER DATABASE - Query users ###");
        var users = userRepository.findAll();

        System.out.println("### LAYER DATABASE - Users: " + users);
        assertThat(users).isNotEmpty();
    }

    @When("i do an request GET")
    public void iDoAnRequestGET() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("### LAYER CONTROLLER - Making request GET ###");
        response = testRestTemplate.exchange(PATH_USERS, HttpMethod.GET, new HttpEntity<>(headers),
                UserResponse[].class);

        System.out.println("### LAYER CONTROLLER - Response: " + response);
    }

    @Then("response must be status code {int}")
    public void responseMustBeStatus(int statusCode) {
        System.out.println("### LAYER CONTROLLER - Validate response status code equals 200 ###");
        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    @And("the body must contain a list of users")
    public void theBodyMustContainAListOfUsers() {
        System.out.println("### LAYER CONTROLLER - Validate list of users ###");
        assertThat(response.getBody()).isNotNull();
    }
}
