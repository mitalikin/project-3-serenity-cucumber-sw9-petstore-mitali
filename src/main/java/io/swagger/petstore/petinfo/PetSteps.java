package io.swagger.petstore.petinfo;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.EndPoints;
import io.swagger.petstore.model.PetPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class PetSteps {
    @Step("Creating pet with id:{0},category:{1},name{2},potoUrls:{3},tags:{4},status{5}")
    public ValidatableResponse createNewPetID(int id, HashMap<String, Object> category,
                                              String name, List<String> photoUrls,
                                              List<HashMap<String, Object>> tags, String status) {

        PetPojo petPojo = new PetPojo();
        petPojo.setId(id);
        petPojo.setCategory(category);
        petPojo.setName(name);
        petPojo.setPhotoUrls(photoUrls);
        petPojo.setTags(tags);
        petPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(petPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the pet information with id:{0}")

    public HashMap<String, Object> getUserInfoById(int petId) {

        return SerenityRest.given().log().all()
                .when()
                .pathParam("petId",petId)
                .get(EndPoints.Get_SINGLE_USER_BY_PETID)
                .then()
                .statusCode(200)
                .extract().path("");
    }
    @Step("Updating pet with id:{0},category:{1},name{2},potoUrls:{3},tags:{4},status{5}")
    public ValidatableResponse updatByPetID(int id, HashMap<String, Object> category,
                                            String name, List<String> photoUrls,
                                            List<HashMap<String, Object>> tags, String status) {

        PetPojo petPojo = new PetPojo();
        petPojo.setId(id);
        petPojo.setCategory(category);
        petPojo.setName(name);
        petPojo.setPhotoUrls(photoUrls);
        petPojo.setTags(tags);
        petPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(petPojo)
                .when()
                .put()
                .then();
    }
    @Step("Deleting pet information with id: {0}")

    public ValidatableResponse deletePetById(int petId) {
        return SerenityRest.given().log().all()
                .pathParam("petId", petId)
                .when()
                .delete(EndPoints.Delete_SINGLE_USER_BY_PETID)
                .then();
    }
    @Step("Getting pet information with Id: {0}")
    public ValidatableResponse getPetById(int petId) {
        return SerenityRest.given().log().all()
                .pathParam("petId", petId)
                .when()
                .get(EndPoints.Get_SINGLE_USER_BY_PETID)
                .then();
    }




}
