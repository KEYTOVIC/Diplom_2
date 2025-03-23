package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {

    public static Response createOrderWithAuth(String token, String[] ingredients) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{\"ingredients\": " + formatIngredients(ingredients) + "}")
                .post(Config.ORDERS_URL);
    }

    public static Response createOrderWithoutAuth(String[] ingredients) {
        return given()
                .header("Content-Type", "application/json")
                .body("{\"ingredients\": " + formatIngredients(ingredients) + "}")
                .post(Config.ORDERS_URL);
    }

    public static Response getOrdersWithAuth(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .get(Config.ORDERS_URL);
    }

    public static Response getOrdersWithoutAuth() {
        return given()
                .get(Config.ORDERS_URL);
    }

    private static String formatIngredients(String[] ingredients) {
        StringBuilder formatted = new StringBuilder("[");
        for (int i = 0; i < ingredients.length; i++) {
            formatted.append("\"").append(ingredients[i]).append("\"");
            if (i < ingredients.length - 1) {
                formatted.append(",");
            }
        }
        formatted.append("]");
        return formatted.toString();
    }
}
