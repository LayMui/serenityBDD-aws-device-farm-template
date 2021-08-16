package demo.utility;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.awaitility.Awaitility;
import org.hamcrest.CoreMatchers;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommonUtility {

    private final Logger log = LoggerFactory.getLogger(CommonUtility.class);
    private static final long WAITTIME = 2000;
    EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    public String getSMSOTPFromOutBox(Actor actor) {
        String OTP;
        actor.attemptsTo(Get.resource("/outbox/sms").with(request -> {
            return request.relaxedHTTPSValidation();
        }));
        Awaitility.with().pollInSameThread().await().atMost(4, TimeUnit.MINUTES).untilAsserted(() ->
                assertThat(SerenityRest.lastResponse().getStatusCode(), CoreMatchers.is(200)));
        String payloadMessage = SerenityRest.then().extract().path("payload.message").toString();
        log.info("Payload message: " + payloadMessage);
        Pattern p = Pattern.compile("\\s[\\d+]{6}\\s");
        Matcher m = p.matcher(payloadMessage);
        while (m.find()) {
            OTP = m.group();
            return OTP;
        }
        return null;
    }

    public String getExpiredSMSOTPFromOutBox(Actor actor) {
        String OTP = null;
        actor.attemptsTo(Get.resource("/outbox/sms").with(request -> {
            return request.relaxedHTTPSValidation();
        }));
        Awaitility.with().pollInSameThread().await().atMost(4, TimeUnit.MINUTES).untilAsserted(() ->
                assertThat(SerenityRest.lastResponse().getStatusCode(), CoreMatchers.is(200)));
        String payloadMessage = SerenityRest.then().extract().path("payload.message").toString();
        log.info("Payload message: " + payloadMessage);
        Pattern p = Pattern.compile("\\s[\\d+]{6}\\s");
        Matcher m = p.matcher(payloadMessage);
        //Getting the previous OTP
        int i=1;
        while (m.find()) {
            OTP = m.group();
            log.info("OTP"+i+" : " + OTP);
            if(i==2)
                break;
            i++;
        }
        return OTP;
    }

    public static IOSDriver<MobileElement> iosDriver() {
        return (IOSDriver<MobileElement>)
                ((WebDriverFacade) getDriver()).getProxiedDriver();
    }

    public static AndroidDriver<MobileElement> androidDriver() {
        return (AndroidDriver<MobileElement>)
                ((WebDriverFacade) getDriver()).getProxiedDriver();
    }

    public static boolean isAndroid() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        return environmentVariables.getProperty("appium.platformName").equalsIgnoreCase("Android");
    }

    public static void scrollUpScreen(int howManySwipes) {
        Dimension size;
        MobileDriver driver;
        if (isAndroid()) {
            driver = androidDriver();
        } else {
            driver = iosDriver();
        }
        size = driver.manage().window().getSize();
        int startVerticalY = (int) (size.height * 0.8);
        int endVerticalY = (int) (size.height * 0.21);
        int startVerticalX = (int) (size.width / 2.1);
        for (int i = 1; i <= howManySwipes; i++) {
            new TouchAction((driver))
                    .press(PointOption.point(startVerticalX, startVerticalY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(WAITTIME)))
                    .moveTo(PointOption.point(startVerticalX, endVerticalY)).release().perform();
        }
    }

    public String setEnvironmentProperty() {
            return environmentVariables.optionalProperty("outboxUrl")
                    .orElse("https://msg-dev-vpce.uat3.test.aws.sg.singtelgroup.net");
    }
}


