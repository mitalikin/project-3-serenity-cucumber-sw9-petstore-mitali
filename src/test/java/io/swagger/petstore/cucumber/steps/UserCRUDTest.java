package io.swagger.petstore.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.petinfo.UserSteps;
import io.swagger.petstore.utils.TestUtils;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class UserCRUDTest {
    static String userName = null;
    static ValidatableResponse response;

    static int userID;



    @Steps
    UserSteps userSteps;
    @When("^I create a new user by providing the information id \"([^\"]*)\" username \"([^\"]*)\" firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\"password\"([^\"]*)\"phone\"([^\"]*)\"userStatus\"([^\"]*)\"$")
    public void iCreateANewUserByProvidingTheInformationIdUsernameFirstNameLastNameEmailPasswordPhoneUserStatus(int _userID, String _userName, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        userName = TestUtils.getRandomValue()+ _userName;
        response = userSteps.createUser(_userID,userName,firstName,lastName,email,password,phone,userStatus);
    }

    @Then("^I verify that the user with \"([^\"]*)\" is created$")
    public void iVerifyThatTheStudentWithIsCreated(String arg0) {
        response.statusCode(200);
        HashMap<String, Object> userMap = userSteps.getUserInfoByUsername(userName);
        Assert.assertThat(userMap, hasValue(userName));
        userID = (int) userMap.get("id");
    }

    @Given("^User information is running$")
    public void userInformationIsRunning() {
    }


    @And("^I update the user with information id \"([^\"]*)\" username \"([^\"]*)\" firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\"password\"([^\"]*)\"phone\"([^\"]*)\"userStatus\"([^\"]*)\"$")
    public void iUpdateTheUserWithInformationIdUsernameFirstNameLastNameEmailPasswordPhoneUserStatus(int _userID, String _userName, String firstName, String lastName, String email, String password, String phone, int userStatus) {
    userName=userName+"_updated";
    response=userSteps.updateUser(_userID,userName,firstName,lastName,email,password,phone,userStatus);
    }

    @And("^The user with username \"([^\"]*)\" is updated successfully$")
    public void theUserWithUsernameIsUpdatedSuccessfully(String arg0)  {
        HashMap<String,Object> studentMap = userSteps.getUserInfoByUsername(userName);
        Assert.assertThat(studentMap, hasValue(userName));
    }

    @And("^I delete the user that created with username\"([^\"]*)\"$")
    public void iDeleteTheUserThatCreatedWithUsername(String arg0)  {
response=userSteps.deleteUser(userName);
    }

    @Then("^The user deleted successfully$")
    public void theUserDeletedSuccessfully() {
        response.statusCode(204);
        userSteps.getUserById(userName).statusCode(404);
    }
}
