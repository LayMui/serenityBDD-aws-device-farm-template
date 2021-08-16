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

public class EnterCredentials {


    public static Performable mobileNumber(String mobileNumber) {
        return Task.where(
                "{0} attempts to enter #mobileNumber",
                until(the(LoginPage.LOGIN_MOBILE_NUMBER), isVisible()).forNoLongerThan(30).seconds(),
                Click.on(LoginPage.LOGIN_MOBILE_NUMBER),
                Enter.theValue(mobileNumber).into(LoginPage.LOGIN_MOBILE_NUMBER_EDIT_TEXT).thenHit(Keys.ENTER));
    }


}


