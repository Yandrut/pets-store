package org.yandrut.API;

import org.testng.annotations.Test;
import org.yandrut.data.PetData;
import org.yandrut.data.PetDataResponse;
import org.yandrut.data.ResponseWrapper;
import org.yandrut.utils.DataReader;
import org.yandrut.utils.PetDataCreator;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PetTest {
    private static PetData petData;
    private static final String PET_ENDPOINT = DataReader.getTestData("endpoints.pet");

    @Test
    public void allowsAddingNewPet() {
        petData = PetDataCreator.createNewPet();

        PetDataResponse petDataResponse = given()
                .body(petData)
                .when()
                .post(PET_ENDPOINT)
                .then().log().all()
                .extract().as(PetDataResponse.class);

        String expected = DataReader.getTestData("pet.name");
        String actual = petDataResponse.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void allowsUpdatingPetsImage() {
        List<String> photoURLs = List.of(DataReader.getTestData("pet.updatedImages"));
        petData.updatePetsInformation(photoURLs);

        PetDataResponse petDataResponse = given()
                .body(petData)
                .when()
                .put(PET_ENDPOINT)
                .then().log().all()
                .extract().as(PetDataResponse.class);

        List<String> photoURLsActual = petDataResponse.getPhotoUrls();
        assertEquals(photoURLs, photoURLsActual);
    }

    @Test
    public void allowsUpdatingPetsNameAndStatus() {
        String updatedName = DataReader.getTestData("pet.updatedName");
        petData.updatePetsInformation(updatedName, DataReader.getTestData("pet.updatedStatus"));
        PetDataResponse petDataResponse = given()
                .body(petData)
                .when()
                .put(PET_ENDPOINT)
                .then().log().all()
                .extract().as(PetDataResponse.class);

        String actual = petDataResponse.getName();

        assertEquals(updatedName, actual);
    }

    @Test
    public void allowsDeletingPet() {
        Integer petId = petData.getId();
        String pathParam = DataReader.getTestData("params.allowsToDelete.pathParam");

        ResponseWrapper responseWrapper = given()
                .pathParam(pathParam, petId)
                .when()
                .delete(PET_ENDPOINT+"/{petId}")
                .then().log().all()
                .extract().as(ResponseWrapper.class);

        String expected = petData.getId().toString();
        String actual = responseWrapper.getMessage();

        assertEquals(expected, actual);
    }
}