package tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;
import utils.UserApi;

public class UserTests {

    @Test
    @DisplayName("Авторизация с верными данными")
    public void testLoginWithValidCredentials() {
        UserApi.loginUser("test-data@yandex.ru", "password")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    @DisplayName("Авторизация с неверными данными")
    public void testLoginWithInvalidCredentials() {
        UserApi.loginUser("wrong-email@yandex.ru", "wrongPassword")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
