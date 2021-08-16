package demo.stepdefinitions;
import demo.tasks.Confirms;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Steps;

import demo.utility.CommonUtility;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginStepDefinitions {

    @Steps
    CommonUtility commonUtility ;
    private final Logger log = LoggerFactory.getLogger(LoginStepDefinitions.class);
    Actor james;

    @Before
    public void setTheStage()  {
        OnStage.setTheStage(new OnlineCast());
        james = Actor.named("James the mobile user").
                whoCan(CallAnApi.at(commonUtility.setEnvironmentProperty()));

    }

    @After()
    public void drawTheCurtain() {
        OnStage.drawTheCurtain();
    }


    @Given("^(.*) is at the app$")
    public void jamesIsAtTheApp(String actor) {
        theActorCalled(actor)
        theActorInTheSpotlight().attemptsTo(Confirms.termsAndConditions());
    }
}
