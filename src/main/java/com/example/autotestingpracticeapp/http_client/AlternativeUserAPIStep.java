package com.example.autotestingpracticeapp.http_client;

import com.example.autotestingpracticeapp.model_pojo.User;
import com.example.autotestingpracticeapp.model_pojo.UserInput;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AlternativeUserAPIStep {
    private final AlternativeUserHTTPRequests alternativeUserHTTPRequests = new AlternativeUserHTTPRequests();

    public List<User> getUsersListOk200() {
        return alternativeUserHTTPRequests.doGetUsers().then().statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().getList("", User.class);
        // или такой вариант получения списка
//        return request.doGetUsers().then().extract().body().as(new TypeRef<List<>>() {
//        });
    }

    public User getUserByIdOk200(UUID id) {
        return alternativeUserHTTPRequests.doGetUsers(id).then().statusCode(HttpStatus.SC_OK).
                extract().body().as(User.class);
    }

    public UUID createUserOk200(UserInput input) {
        return alternativeUserHTTPRequests.doPostUsers(input).then().statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().getUUID("id");
    }

    public User editeUserOk200(UUID id, UserInput input) {
        return alternativeUserHTTPRequests.doPutUsers(id, input).then().statusCode(HttpStatus.SC_OK)
                .extract().body().as(User.class);
    }

    public void deleteUser(UUID id) {
        alternativeUserHTTPRequests.doDeleteUsers(id).assertThat().statusCode(HttpStatus.SC_OK);
    }
}
