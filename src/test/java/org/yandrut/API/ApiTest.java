package org.yandrut.API;
import com.google.gson.Gson;
import org.testng.annotations.Test;
import org.yandrut.data.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.yandrut.data.Specifications.*;
import static io.restassured.RestAssured.given;

public class ApiTest {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    private static final String USER_ENDPOINT = "/user";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String USER_LIST_ENDPOINT = "/createWithList";
    private static final String LOGOUT_ENDPOINT = "/logout";
    private static final String CREATE_PET_ENDPOINT = "/pet";

    @Test
    public void allowsCreatingUser() {
        submitSpecifications(requestSpec(BASE_URL), responseSpec(200));

        UserData userData = new UserData(1488, "Yandrut","Yandrut",
                "Yandruter", "yandrut@yandrut.com", "yandrutYandrut",
                "380671234567", 1488);

        UserDataResponse response = given()
                .body(userData)
                .when()
                .post(USER_ENDPOINT)
                .then().log().all()
                .extract().as(UserDataResponse.class);

        String expected = "unknown";
        String actual = response.getType();

        assertEquals(expected, actual);
    }

    @Test
    public void allowsLoginUser() {
        submitSpecifications(requestSpec(BASE_URL), responseSpec(200));

        LoginData loginData = new LoginData("Yandrut", "yandrutYandrut");
        String loginDataToJson = new Gson().toJson(loginData);

        LoginDataResponse response = given()
                .queryParam("credentials", loginDataToJson)
                .when()
                .get(USER_ENDPOINT + LOGIN_ENDPOINT)
                .then().log().all()
                .extract().as(LoginDataResponse.class);

        String expectedResponse = response.getMessage();
        assertTrue(expectedResponse.contains("logged in user session:"));
    }

    @Test
    public void allowsCreatingListOfUsers() {
        submitSpecifications(requestSpec(BASE_URL), responseSpec(200));

        UserData userData = new UserData(1488, "Yandrut","Yandrut",
                "Yandrut", "yandrut@yandrut.com", "yandrutYandrut",
                "380671234567", 1488);

        List <UserData> userDataList = Arrays.asList(userData, userData, userData, userData);
        String userDataListToJson = new Gson().toJson(userDataList);

        ListOfUsersResponse response = given()
                .body(userDataListToJson)
                .when()
                .post(USER_ENDPOINT + USER_LIST_ENDPOINT)
                .then().log().all()
                .extract().as(ListOfUsersResponse.class);

        String responseMessage = response.getMessage();
        assertTrue(responseMessage.equals("ok"));
    }

    @Test
    public void allowsLogOutUser() {
        submitSpecifications(requestSpec(BASE_URL), responseSpec(200));

        LogOutUserResponse response = given()
                .when()
                .get(USER_ENDPOINT + LOGOUT_ENDPOINT)
                .then().log().all()
                .extract().as(LogOutUserResponse.class);

        String responseMessage = response.getMessage();
        assertTrue(responseMessage.equals("ok"));
    }

    @Test
    public void allowsAddingNewPet() {
        submitSpecifications(requestSpec(BASE_URL), responseSpec(200));

        PetData pet = PetData.createPet(1488, "John",
                List.of("https://ideyka.com.ua/files/resized/products/4484.1800x1800w.jpg"),
                "available");

        PetDataResponse response = given()
                .body(pet)
                .when()
                .post(CREATE_PET_ENDPOINT)
                .then().log().all()
                .extract().as(PetDataResponse.class);

        String expected = "John";
        String actual =  response.getName();
        assertEquals(expected, actual);
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