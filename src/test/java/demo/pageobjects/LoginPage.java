package demo.pageobjects;

import io.appium.java_client.MobileBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class LoginPage extends PageObject {
    public static final Target LOGIN_LOGINBTN = Target.the("Log in").located(MobileBy.AccessibilityId("login_button"));

    public static final Target LOGIN_MOBILE_NUMBER = Target.the("mobile number")
        .locatedForAndroid(MobileBy.xpath("//*[@text='mobile number']"))
        .locatedForIOS(MobileBy.AccessibilityId("textinput_touchable"));

        public static final Target LOGIN_MOBILE_NUMBER_EDIT_TEXT = Target.the("mobile number edit")
        .locatedForAndroid(MobileBy.xpath("//*[@text='mobile number']/following::*[@class='android.widget.EditText']"))
        .locatedForIOS(MobileBy.AccessibilityId("mobile_touchable"));

}