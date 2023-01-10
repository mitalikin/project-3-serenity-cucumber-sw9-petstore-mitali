package io.swagger.petstore.petinfo;

import io.swagger.petstore.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@Concurrent(threads = "2x")
@UseTestDataFrom("src/test/java/resources/testdata/petinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class UserDataDrivenTest extends TestBase {
    private int id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public int userStatus;
    @Steps
    UserSteps userSteps;
    @Title("Data driven test for adding multiple user to the application")
    @Test
    public void createMultipleUser(){
        userSteps.createUser(id,username,firstName,lastName,email,password,phone,userStatus).statusCode(200);
    }
}
