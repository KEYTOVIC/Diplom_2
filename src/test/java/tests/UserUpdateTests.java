package tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import utils.Config;
import utils.UserApi;

public class UserUpdateTests {

    @Test
    @DisplayName("Обновление данных пользователя с авторизацией")
    public void testUpdateUserWithAuth() {
        UserApi.updateUserData(Config.TEST_TOKEN, "new-email@yandex.ru", "NewName")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo("new-email@yandex.ru"))
                .body("user.name", equalTo("NewName"));
    }

    @Test
    @DisplayName("Обновление данных пользователя без авторизации")
    public void testUpdateUserWithoutAuth() {
        UserApi.updateUserData("", "new-email@yandex.ru", "NewName")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }


}
