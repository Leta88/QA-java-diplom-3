package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import static io.restassured.RestAssured.given;

public class UserApi extends RestApi{

    public static final String CREATE_USER_URI = "/api/auth/register";
    public static final String LOGIN_USER_URI = "/api/auth/login";
    public static final String ACT_USER_URI = "/api/auth/user";

    @Step("Create user")
    public ValidatableResponse createUser(UserData user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(CREATE_USER_URI)
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse deleteUser(String token){
        return given()
                .spec(requestSpecification())
                .and()
                .header("Authorization", token)
                .when()
                .delete(ACT_USER_URI)
                .then();
    }

    @Step("Login")
    public ValidatableResponse loginUser(UserData user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(LOGIN_USER_URI)
                .then();
    }
}

