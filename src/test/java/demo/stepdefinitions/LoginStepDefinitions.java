package demo.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginStepDefinitions {

    @Steps
    private final Logger log = LoggerFactory.getLogger(LoginStepDefinitions.class);
    Actor james;

    @Before
    public void setTheStage()  {
        OnStage.setTheStage(new OnlineCast());
  
    }

    @After()
    public void drawTheCurtain() {
        OnStage.drawTheCurtain();
    }


    @Given("^(.*) is at the app$")
    public void jamesIsAtTheApp(String actor) {
        theActorCalled(actor)
    }
}
