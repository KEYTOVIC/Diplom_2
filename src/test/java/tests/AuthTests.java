package tests;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utils.Config;
import utils.UserApi;

public class AuthTests {
    @Test
    @DisplayName("Логин под существующим пользователем")
    public void testLoginWithValidCredentials() {
        UserApi.loginUser(Config.VALID_EMAIL, Config.VALID_PASSWORD)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    @DisplayName("Логин с неверным логином и паролем")
    public void testLoginWithInvalidCredentials() {
        UserApi.loginUser("wrong@yandex.ru", "wrongpassword")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}