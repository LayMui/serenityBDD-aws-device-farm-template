package demo.stepdefinitions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import demo.tasks.EnterCredentials;


import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

    @When("^he provides his mobile number \"(.*)\"$")
    public void heProvidesNetworkOrOTPMobileNumber(String mobile) throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(EnterCredentials.mobileNumber(mobile));
     

    @Then("^he is unable to login to the app services$")
    public void heIsUnableToLoginToTheMSTAAppServices() {
       
    }
}
