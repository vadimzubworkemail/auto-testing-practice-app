package com.example.autotestingpracticeapp.api_test;

import com.example.autotestingpracticeapp.model_pojo.User;
import com.example.autotestingpracticeapp.model_pojo.UserInput;
import com.example.autotestingpracticeapp.rest.UserHTTPRequests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserAPITest {
    Response response;
    UserHTTPRequests requests;
    UUID id;

    @BeforeEach
    public void setRequests() {
        requests = new UserHTTPRequests();
    }

    @AfterEach
    public void tearDown() {
        if (response != null) {
            requests.deleteUser(id);
        }
    }

    @Test
    public void checkUserListIsNotEmptyOk200() {
        Response response = requests.getUsersList()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<UUID> uuids = jsonPath.getList("id");
        List<String> logins = jsonPath.getList("u_login");
        List<String> email = jsonPath.getList("email");
        assertThat(uuids).isNotEmpty();
        assertThat(logins).isNotEmpty();
        assertThat(email).isNotEmpty();
    }

    @Test
    public void checkingTheUserByIdOk200() {
        int index = 0;
        Response response = requests.getUsersList().statusCode(200).log().all().extract().response();
        JsonPath jsonPath = response.jsonPath();
        UUID id = UUID.fromString(jsonPath.getList("id").get(index).toString());
        String login = jsonPath.getList("u_login").get(index).toString();
        String email = jsonPath.getList("email").get(index).toString();

        Response request = requests.getUserById(id).assertThat().statusCode(200)
                .log().all().extract().response();
        JsonPath actualData = request.jsonPath();

        assertThat(id).isEqualTo(UUID.fromString(actualData.get("id").toString()));
        assertThat(login).isEqualTo(actualData.get("u_login"));
        assertThat(email).isEqualTo(actualData.get("email"));
    }

    @Test
    public void checkUserCreationOk200() {
        final UserInput userInput = new UserInput("Expected_Login", "Expected_Email");

        response = requests.createUser(userInput).log().all()
                .assertThat().statusCode(200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        id = UUID.fromString(jsonPath.get("id").toString());

        assertThat(userInput.getU_login()).isEqualTo(jsonPath.get("u_login"));
        assertThat(userInput.getEmail()).isEqualTo(jsonPath.get("email"));
    }

    @Test
    public void userDeletionCheck() {
        final UserInput userInput = new UserInput("Expected_Login", "Expected_Email");

        User user = requests.createUser(userInput).log().all().statusCode(200)
                .extract().body().as(User.class);
        UUID id = UUID.fromString(user.getId().toString());
        requests.deleteUser(id);
    }

    @Test
    public void checkUserEditionOk200() {
        final UserInput userInput = new UserInput("user_login", "user_email");
        response = requests.createUser(userInput).log().all()
                .assertThat().statusCode(200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        id = UUID.fromString(jsonPath.get("id").toString());
        final UserInput editUser = new UserInput("edit_login", "edit_email");
        response = requests.editUser(id, editUser).log().all().assertThat().statusCode(200)
                .extract().response();
        JsonPath actualData = response.jsonPath();

        assertThat(editUser.getU_login()).isEqualTo(actualData.get("u_login"));
        assertThat(editUser.getEmail()).isEqualTo(actualData.get("email"));
    }
}
