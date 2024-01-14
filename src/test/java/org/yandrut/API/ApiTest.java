package org.yandrut.API;
import com.google.gson.Gson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.yandrut.data.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.given;

public class ApiTest {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    private static final String USER_ENDPOINT = "/user";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String USER_LIST_ENDPOINT = "/createWithList";
    private static final String LOGOUT_ENDPOINT = "/logout";
    private static final String PET_ENDPOINT = "/pet";

    private static PetData pet;

    @BeforeMethod
    public void buildAndSubmitSpecifications() {
        Specifications.buildSpecifications(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(200));
    }

    @Test
    public void allowsCreatingUser() {
        UserData userData = new UserData(1488, "Yandrut", "Yandrut",
                "Yandruter", "yandrut@yandrut.com", "yandrutYandrut",
                "380671234567", 1488);

        ResponseWrapper responseBody = given()
                .body(userData)
                .when()
                .post(USER_ENDPOINT)
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String expected = "unknown";
        String actual = responseBody.getType();

        assertEquals(expected, actual);
    }

    @Test
    public void allowsLoginUser() {
        LoginData loginData = new LoginData("Yandrut", "yandrutYandrut");
        String loginDataToJson = new Gson().toJson(loginData);

        ResponseWrapper responseBody = given()
                .queryParam("credentials", loginDataToJson)
                .when()
                .get(USER_ENDPOINT + LOGIN_ENDPOINT)
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String expectedResponse = responseBody.getMessage();
        assertTrue(expectedResponse.contains("logged in user session:"));
    }

    @Test
    public void allowsCreatingListOfUsers() {
        UserData userData = new UserData(1488, "Yandrut","Yandrut",
                "Yandrut", "yandrut@yandrut.com", "yandrutYandrut",
                "380671234567", 1488);

        List <UserData> userDataList = Arrays.asList(userData, userData, userData, userData);
        String userDataListToJson = new Gson().toJson(userDataList);

        ResponseWrapper responseBody = given()
                .body(userDataListToJson)
                .when()
                .post(USER_ENDPOINT + USER_LIST_ENDPOINT)
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String responseMessage = responseBody.getMessage();
        assertEquals("ok", responseMessage);
    }

    @Test
    public void allowsLogOutUser() {
        ResponseWrapper responseBody = given()
                .when()
                .get(USER_ENDPOINT + LOGOUT_ENDPOINT)
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String responseMessage = responseBody.getMessage();
        assertEquals("ok", responseMessage);
    }

    @Test
    public void allowsAddingNewPet() {
                pet = PetData.createNewPet(1488, "John",
                List.of("https://ideyka.com.ua/files/resized/products/4484.1800x1800w.jpg"),
                "available");

        PetDataResponse responseBody = given()
                .body(pet)
                .when()
                .post(PET_ENDPOINT)
                .then().log().all()
                .extract().as(PetDataResponse.class);

        String expected = "John";
        String actual =  responseBody.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void allowsUpdatingPetsImage() {
        List<String> photoURLs = List.of("https://img.tsn.ua/cached/754/tsn-2cb3382837f9b91c3a25a3f20b5ee88b/thumbs/428x268/78/f1/7dfbdb544885b3f8c57c9a2b6e0bf178.jpg");
        pet.updatePetsInformation(photoURLs);

        PetDataResponse responseBody = given()
                .body(pet)
                .when()
                .put(PET_ENDPOINT)
                .then().log().all()
                .extract().as(PetDataResponse.class);

        List<String> photoURLsActual = responseBody.getPhotoUrls();
        assertEquals(photoURLs, photoURLsActual);
    }

    @Test
    public void allowsUpdatingPetsNameAndStatus() {
        pet.updatePetsInformation("Pushok", "not online");
        PetDataResponse responseBody = given()
                .body(pet)
                .when()
                .put(PET_ENDPOINT)
                .then().log().all()
                .extract().as(PetDataResponse.class);

        String expected = "Pushok";
        String actual = responseBody.getName();

        assertEquals(expected, actual);
    }

    @Test
    public void allowsDeletingPet() {
        Integer petId = pet.getId();
        ResponseWrapper responseBody = given()
                .pathParam("petId", petId)
                .when()
                .delete(PET_ENDPOINT+"/{petId}")
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String expected = pet.getId().toString();
        String actual = responseBody.getMessage();

        assertEquals(expected, actual);
    }
}