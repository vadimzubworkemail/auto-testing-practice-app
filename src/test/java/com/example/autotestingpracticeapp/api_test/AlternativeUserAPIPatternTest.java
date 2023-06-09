package com.example.autotestingpracticeapp.api_test;

import com.example.autotestingpracticeapp.http_client.AlternativeUserAPIStep;
import com.example.autotestingpracticeapp.model_pojo.User;
import com.example.autotestingpracticeapp.model_pojo.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AlternativeUserAPIPatternTest {
    @Autowired
    AlternativeUserAPIStep step;

    UUID id;

    @BeforeEach
    public void setUp() {
        if (id != null) {
            step.deleteUser(id);
        }
    }

    @Test
    void checkThatTheUsersListIsNotEmpty() {
        assertThat(step.getUsersListOk200()).isNotNull();
    }

    @Test
    void shouldGetTheUserById() {
        int index = 0;
        UUID expectedId = step.getUsersListOk200().get(index).getId();
        String expectedLogin = step.getUsersListOk200().get(index).getU_login();
        String expectedEmail = step.getUsersListOk200().get(index).getEmail();

        User actualUser = step.getUserByIdOk200(expectedId);
        assertThat(actualUser.getU_login()).isEqualTo(expectedLogin);
        assertThat(actualUser.getEmail()).isEqualTo(expectedEmail);
    }

    @Test
    void checkUserCreation() {
        UserInput expectedUser = new UserInput("expected_login", "expected_email");
        id = step.createUserOk200(expectedUser);

        assertThat(expectedUser.getEmail()).isEqualTo(step.getUserByIdOk200(id).getEmail());
        assertThat(expectedUser.getU_login()).isEqualTo(step.getUserByIdOk200(id).getU_login());
    }

    @Test
    void checkUserEdition() {
        UserInput user = new UserInput("expected_login", "expected_email");
        id = step.createUserOk200(user);

        UserInput expectedData = new UserInput("edited_login", "edited_email");
        User actualUser = step.editeUserOk200(id, expectedData);

        assertThat(expectedData.getU_login()).isEqualTo(actualUser.getU_login());
        assertThat(expectedData.getEmail()).isEqualTo(actualUser.getEmail());
    }

    @Test
    void checkUserDeletion() {
        UserInput user = new UserInput("expected_login", "expected_email");
        id = step.createUserOk200(user);
        step.deleteUser(id);

        List<User> users = step.getUsersListOk200();
        for (User userOutput : users) {
            assertThat(id).isNotEqualTo(userOutput.getId());
        }
    }
}
