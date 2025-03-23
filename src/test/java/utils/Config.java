package utils;

public class Config {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    public static final String REGISTER_URL = BASE_URL + "/auth/register";
    public static final String LOGIN_URL = BASE_URL + "/auth/login";
    public static final String LOGOUT_URL = BASE_URL + "/auth/logout";
    public static final String USER_URL = BASE_URL + "/auth/user";
    public static final String TOKEN_URL = BASE_URL + "/auth/token";

    public static final String ORDERS_URL = BASE_URL + "/orders";
    public static final String INGREDIENTS_URL = BASE_URL + "/ingredients";

    public static final String VALID_EMAIL = "test-data@yandex.ru";
    public static final String VALID_PASSWORD = "password";

    public static String TEST_TOKEN = "";
}

