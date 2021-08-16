package demo.questions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import demo.pageobjects.*;

public class VerifyMessage {

    public static Question<String> displayText(Target target) {
        theActorInTheSpotlight().attemptsTo(Wait.until(
                WebElementQuestion.the(target) , WebElementStateMatchers.isEnabled()
        ).forNoLongerThan(30).seconds());
        return actor -> Text.of(target).viewedBy(actor).asString();
    }
    public static Performable displayError(Target target ,String msg) {
        return Task.where(
                "{0} Verify Error Message",
                WaitUntil.the(target, isVisible()).forNoMoreThan(30).seconds(),
                Ensure.that(target).hasText(msg));
    }

   

}
