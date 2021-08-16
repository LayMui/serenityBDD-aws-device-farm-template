package demo.tasks;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;


import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static net.serenitybdd.screenplay.waits.Wait.until;
public class Confirms {
    public static Performable termsAndConditions() {
        return Task.where(
                "{0} attempts to click on terms and conditions",
                WaitUntil.the(TermsAndConditions.TNC_I_AGREE_BTN, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(TermsAndConditions.TNC_I_AGREE_BTN));
    }

}
