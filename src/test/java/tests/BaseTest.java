package tests;

import driver.Driver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.support.ui.WebDriverWait;
import screens.InitialGmailScreen;
import screens.NewEmailScreen;
import screens.OnboardingScreen;
import utils.JsonParser;

import java.io.IOException;
import java.net.MalformedURLException;

public class BaseTest {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    protected static OnboardingScreen onboardingScreen;
    protected static NewEmailScreen newEmailScreen;
    protected static InitialGmailScreen initialGmailScreen;
    protected static String subject;
    protected static String body;

    @BeforeAll
    public static void initialDriver() throws MalformedURLException {
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, 10, 5);
        onboardingScreen = OnboardingScreen.getOnboardingScreen(driver, wait);
        newEmailScreen = NewEmailScreen.getNewEmailScreen(driver, wait);
        initialGmailScreen = InitialGmailScreen.getInitialGmailScreen(driver, wait);

        subject = JsonParser.getSubject();
        body = JsonParser.getBody();
    }


    public void sendMessage() {
        onboardingScreen.completedOnboarding();
        initialGmailScreen.navigateToNewEmailPage();

        newEmailScreen.sendEmail(JsonParser.getTo(), subject, body);
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        onboardingScreen.resetInstance();
        initialGmailScreen.resetInstance();
        newEmailScreen.resetInstance();
        Driver.closeDriver(driver);
        Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
        Runtime.getRuntime().exec("adb kill-server");
    }
}
