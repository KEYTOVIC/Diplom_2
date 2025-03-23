package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response registerUser(String email, String password, String name) {
        return given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\"}")
                .post(Config.REGISTER_URL);
    }

    public static Response loginUser(String email, String password) {
        return given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .post(Config.LOGIN_URL);
    }

    public static Response getUserData(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .get(Config.USER_URL);
    }

    public static Response updateUserData(String token, String newEmail, String newName) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{\"email\": \"" + newEmail + "\", \"name\": \"" + newName + "\"}")
                .patch(Config.USER_URL);
    }

    public static Response deleteUser(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .delete(Config.USER_URL);
    }
}


