package io.swagger.petstore.petinfo;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;
//@RunWith(SerenityRunner.class)
public class PetCRUDTestWithSteps extends TestBase {
    static int id = 1235;
    static String name = "doggie";
    // static HashMap<String, Object> category;
    // static List<String> photoUrls;
    // static List<HashMap<String, Object>> tags;
    static String status = "available";
    @Steps
    PetSteps petSteps;

    @Title("This will create a new petID")
    @Test
    public void test001() {

        List<String> photo = new ArrayList<>();
        photo.add("xyz");
        HashMap<String, Object> category = new HashMap<String, Object>();
        category.put("id", "102");
        category.put("name", "Max");
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("abc");
        List<HashMap<String, Object>> tags = new ArrayList<>();
        tags.add(category);
        ValidatableResponse response = petSteps.createNewPetID(id, category, name, photoUrls, tags, status);
        response.log().all().statusCode(200);
    }

    @Title("verify if pet added to the application")
    @Test
    public void test002() {

        HashMap<String, Object> userMap = petSteps.getUserInfoById(id);
        Assert.assertThat(userMap, hasValue(id));
    }

    @Title("Update the pet information and verify the updated information")
    @Test
    public void test003() {
        List<String> photo = new ArrayList<>();
        photo.add("xyz");
        HashMap<String, Object> category = new HashMap<String, Object>();
        category.put("id", "102");
        category.put("name", "Max");
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("abc");
        List<HashMap<String, Object>> tags = new ArrayList<>();
        tags.add(category);

        name = name + "_updated";
        status = status + "_updated";
        petSteps.updatByPetID(id, category, name, photoUrls, tags, status).log().all().statusCode(200);
        HashMap<String, Object> userMap = petSteps.getUserInfoById(id);
        Assert.assertThat(userMap, hasValue(id));
    }

    @Title("Delete the pet and verify if the user is deleted!")
    @Test
    public void test004() {
        petSteps.deletePetById(id).statusCode(200);
        petSteps.getPetById(id).statusCode(404);
    }

}
