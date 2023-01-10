package io.swagger.petstore.cucumber;

import cucumber.api.CucumberOptions;
import io.swagger.petstore.testbase.TestBase;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature/")
public class CucumberRunner extends TestBase {

}
