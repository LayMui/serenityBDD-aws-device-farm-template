package demo.pageobjects;

import io.appium.java_client.MobileBy;
import net.serenitybdd.screenplay.targets.Target;

public class TermsAndConditions extends PageObject {
    public static final Target TNC_I_AGREE_BTN = Target.the("I Agree")
            .located(MobileBy.AccessibilityId("agree_to_TnC"));

    
}
