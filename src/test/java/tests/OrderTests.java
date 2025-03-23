package tests;

import io.qameta.allure.Step;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utils.Config;
import utils.OrderApi;

public class OrderTests {
    @Test
    @Step("Создание заказа с авторизацией и ингредиентами")
    public void testCreateOrderWithAuth() {
        OrderApi.createOrderWithAuth(Config.TEST_TOKEN, new String[]{"61c0c5a71d1f82001bdaaa6d"})
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    @Step("Создание заказа без авторизации")
    public void testCreateOrderWithoutAuth() {
        OrderApi.createOrderWithoutAuth(new String[]{"61c0c5a71d1f82001bdaaa6d", "609646e4dc916e00276b2870"})
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @Step("Создание заказа без ингредиентов")
    public void testCreateOrderWithoutIngredients() {
        OrderApi.createOrderWithAuth(Config.TEST_TOKEN, new String[]{})
                .then()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @Step("Создание заказа с неверным хешем ингредиентов")
    public void testCreateOrderWithInvalidIngredientHash() {
        OrderApi.createOrderWithAuth(Config.TEST_TOKEN, new String[]{"123"})
                .then()
                .statusCode(500);
    }

    @Test
    @Step("Получение заказов авторизованного пользователя")
    public void testGetOrdersWithAuth() {
        OrderApi.getOrdersWithAuth(Config.TEST_TOKEN)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("orders", notNullValue());
    }

    @Test
    @Step("Получение заказов неавторизованного пользователя")
    public void testGetOrdersWithoutAuth() {
        OrderApi.getOrdersWithoutAuth()
                .then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}

