package tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utils.Config;
import utils.OrderApi;

public class OrderTests {
    @Test
    @DisplayName("Создание заказа с авторизацией и ингредиентами")
    public void testCreateOrderWithAuth() {
        OrderApi.createOrderWithAuth(Config.TEST_TOKEN, new String[]{"61c0c5a71d1f82001bdaaa6d"})
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void testCreateOrderWithoutAuth() {
        OrderApi.createOrderWithoutAuth(new String[]{"61c0c5a71d1f82001bdaaa6d", "609646e4dc916e00276b2870"})
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void testCreateOrderWithoutIngredients() {
        OrderApi.createOrderWithAuth(Config.TEST_TOKEN, new String[]{})
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST )
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиентов")
    public void testCreateOrderWithInvalidIngredientHash() {
        OrderApi.createOrderWithAuth(Config.TEST_TOKEN, new String[]{"123"})
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Получение заказов авторизованного пользователя")
    public void testGetOrdersWithAuth() {
        OrderApi.getOrdersWithAuth(Config.TEST_TOKEN)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("success", equalTo(true))
                .body("orders", notNullValue());
    }

    @Test
    @DisplayName("Получение заказов неавторизованного пользователя")
    public void testGetOrdersWithoutAuth() {
        OrderApi.getOrdersWithoutAuth()
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}

