package tests;

import io.qameta.allure.Step;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import utils.Config;
import utils.UserApi;

public class UserUpdateTests {

    @Test
    @Step("Обновление данных пользователя с авторизацией")
    public void testUpdateUserWithAuth() {
        UserApi.updateUserData(Config.TEST_TOKEN, "new-email@yandex.ru", "NewName")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo("new-email@yandex.ru"))
                .body("user.name", equalTo("NewName"));
    }

    @Test
    @Step("Обновление данных пользователя без авторизации")
    public void testUpdateUserWithoutAuth() {
        UserApi.updateUserData("", "new-email@yandex.ru", "NewName")
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }


}
