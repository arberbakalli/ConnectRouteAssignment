package stepdefinitions;

import com.google.gson.JsonObject;
import controller.EnvironmentController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.RouteeSteps;

import java.io.IOException;


public class RouteeStepDef {
    @Steps
    private RouteeSteps routeeSteps;

    private JsonObject userPayload;

    @Given("I am authenticated")
    public void iAmAuthenticated() {
        //left empty intentionally
    }

    @When("I create a valid contract")
    public void iCreateAValidContract() throws IOException {
        userPayload = EnvironmentController.getJsonObject("validContact");
        routeeSteps.createValidContact(userPayload);
    }

    @Then("I should see a valid contact confirmation")
    public void iShouldSeeAValidContactConfirmation() {
        routeeSteps.verifyContactCreated();
    }

    @When("I send a valid sms")
    public void iSendAValidSms() throws IOException {
        userPayload = EnvironmentController.getJsonObject("validSmsCampaign");
    }

    @Then("I should see a valid sms confirmation")
    public void iShouldSeeAValidSmsConfirmation() {
    }
}
