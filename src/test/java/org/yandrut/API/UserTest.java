package org.yandrut.API;
import com.google.gson.Gson;
import org.testng.annotations.Test;
import org.yandrut.data.*;
import org.yandrut.utils.DataReader;
import org.yandrut.utils.LoginDataCreator;
import org.yandrut.utils.UserDataCreator;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.given;

public class UserTest extends BaseTest {

    private static final String USER_ENDPOINT = DataReader.getTestData("endpoints.user");
    private static final String LOGIN_ENDPOINT = DataReader.getTestData("endpoints.login");
    private static final String USER_LIST_ENDPOINT = DataReader.getTestData("endpoints.user_list");
    private static final String LOGOUT_ENDPOINT = DataReader.getTestData("endpoints.logout");


    @Test
    public void allowsCreatingUser() {
        UserData userData = UserDataCreator.createUser();

        ResponseWrapper responseWrapper = given()
                .body(userData)
                .when()
                .post(USER_ENDPOINT)
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String expected = DataReader.getTestData("expected.allowsCreatingUser.responseString");
        String actual = responseWrapper.getType();

        assertEquals(expected, actual);
    }

    @Test
    public void allowsLoginUser() {
        LoginData loginData = LoginDataCreator.createLoginData();
        String loginDataToJson = new Gson().toJson(loginData);

        ResponseWrapper responseWrapper = given()
                .queryParam(DataReader.getTestData("params.allowsLoginUser.queryParam"), loginDataToJson)
                .when()
                .get(USER_ENDPOINT + LOGIN_ENDPOINT)
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String expectedResponse = responseWrapper.getMessage();
        assertTrue(expectedResponse.contains(DataReader.getTestData("expected.allowsLoginUser.expectedValue")));
    }

    @Test
    public void allowsCreatingListOfUsers() {
        UserData userData = UserDataCreator.createUser();

        List <UserData> userDataList = Arrays.asList(userData, userData, userData, userData);
        String userDataListToJson = new Gson().toJson(userDataList);

        ResponseWrapper responseWrapper = given()
                .body(userDataListToJson)
                .when()
                .post(USER_ENDPOINT + USER_LIST_ENDPOINT)
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String responseMessage = responseWrapper.getMessage();
        assertEquals(DataReader
                        .getTestData("expected.allowsCreatingListOfUsers.expectedResponseString"),
                responseMessage);
    }

    @Test
    public void allowsLogOutUser() {
        ResponseWrapper responseWrapper = given()
                .when()
                .get(USER_ENDPOINT + LOGOUT_ENDPOINT)
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String responseMessage = responseWrapper.getMessage();
        assertEquals(DataReader.getTestData("expected.allowsCreatingListOfUsers.expectedResponseString"), responseMessage);
    }
}