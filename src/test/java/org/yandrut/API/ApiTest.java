package org.yandrut.API;
import com.google.gson.Gson;
import org.testng.annotations.Test;
import org.yandrut.data.UserData;
import org.yandrut.data.LoginData;

import java.util.Arrays;
import java.util.List;

import static org.yandrut.data.Specifications.*;
import static io.restassured.RestAssured.given;

public class ApiTest {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    private static final String USER_ENDPOINT = "/user";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String USER_LIST_ENDPOINT = "/createWithList";
    private static final String LOGOUT_ENDPOINT = "/logout";

    @Test
    public void allowsCreatingUser() {
        submitSpecifications(requestSpec(BASE_URL));

        UserData userData = new UserData(1488, "Yandrut","Yandrut",
                "Yandruter", "yandrut@yandrut.com", "yandrutYandrut",
                "380671234567", 1488);

        given()
                .body(userData)
                .when()
                .post(USER_ENDPOINT)
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void allowsLoginUser() {
        submitSpecifications(requestSpec(BASE_URL));

        LoginData loginData = new LoginData("Yandrut", "yandrutYandrut");
        String loginDataToJson = new Gson().toJson(loginData);

        given()
                .queryParam("credentials", loginDataToJson)
                .when()
                .get(USER_ENDPOINT + LOGIN_ENDPOINT)
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void allowsCreatingListOfUsers() {
        submitSpecifications(requestSpec(BASE_URL));

        UserData userData = new UserData(1488, "Yandrut","Yandrut",
                "Yandrut", "yandrut@yandrut.com", "yandrutYandrut",
                "380671234567", 1488);

        List <UserData> userDataList = Arrays.asList(userData, userData, userData, userData);
        String userDataListToJson = new Gson().toJson(userDataList);

        given()
                .body(userDataListToJson)
                .when()
                .post(USER_ENDPOINT + USER_LIST_ENDPOINT)
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void allowsLogOutUser() {
        submitSpecifications(requestSpec(BASE_URL));

        given()
                .when()
                .get(USER_ENDPOINT + LOGOUT_ENDPOINT)
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void allowsAddingNewPet() {

    }

    @Test
    public void allowsUpdatingPetsImage() {

    }

    @Test
    public void allowsUpdatingPetsNameAndStatus() {

    }

    @Test
    public void allowsDeletingPet() {

    }
}