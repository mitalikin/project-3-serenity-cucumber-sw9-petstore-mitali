package io.swagger.petstore.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.petinfo.PetSteps;
import io.swagger.petstore.utils.TestUtils;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

public class PetStepdef {
    static ValidatableResponse response;
    static  int petId;
    static  String name;
    @Steps
    PetSteps petSteps;
    @When("^User sends a GET request to petID endpoint$")
    public void userSendsAGETRequestToPetIDEndpoint() {
        response=petSteps.getPetById(petId);
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode(int arg0) {
        response.statusCode(200);
    }

    @When("^I create a new pat by providing the information id \"([^\"]*)\" category \"([^\"]*)\" name \"([^\"]*)\" photoUrls \"([^\"]*)\" tags \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewPatByProvidingTheInformationIdCategoryNamePhotoUrlsTagsStatus(int id, HashMap<String,Object> category, String _name, List<String> photoUrls, List<HashMap<String, Object>> tags, String status) {

        name = TestUtils.getRandomValue()+ _name;
        response = petSteps.createNewPetID(id,category,name,photoUrls,tags,status);
    }

    @Then("^I verify that the pet with \"([^\"]*)\" is created$")
    public void iVerifyThatThePetWithIsCreated(String name)  {
        response.statusCode(201);
        HashMap<String, Object> petMap = petSteps.getUserInfoById(petId);
        petId = (int) petMap.get("id");
        Assert.assertThat(petMap, hasValue(petId));
    }
}
