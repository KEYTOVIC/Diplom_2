package tests;

import io.qameta.allure.Step;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import utils.UserApi;

public class UserTests {

    @Test
    @Step("Авторизация с верными данными")
    public void testLoginWithValidCredentials() {
        UserApi.loginUser("test-data@yandex.ru", "password")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    @Step("Авторизация с неверными данными")
    public void testLoginWithInvalidCredentials() {
        UserApi.loginUser("wrong-email@yandex.ru", "wrongPassword")
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
