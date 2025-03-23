package tests;

import io.qameta.allure.Step;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utils.Config;
import utils.UserApi;

public class AuthTests {
    @Test
    @Step("Логин под существующим пользователем")
    public void testLoginWithValidCredentials() {
        UserApi.loginUser(Config.VALID_EMAIL, Config.VALID_PASSWORD)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    @Step("Логин с неверным логином и паролем")
    public void testLoginWithInvalidCredentials() {
        UserApi.loginUser("wrong@yandex.ru", "wrongpassword")
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}