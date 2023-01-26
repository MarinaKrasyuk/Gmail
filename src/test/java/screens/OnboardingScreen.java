package screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnboardingScreen extends BaseScreen{
    static AndroidDriver driver;
    @FindBy(id = "welcome_tour_got_it")
    private MobileElement gotItButton;
    @FindBy(id = "setup_addresses_list")
    private MobileElement adressList;
    @FindBy(id = "action_done")
    private MobileElement takeMeToGmail;
    @FindBy(id = "next_button")
    private MobileElement nextButton;

    private static OnboardingScreen instance;

    public OnboardingScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
    }

    public static OnboardingScreen getOnboardingScreen(AndroidDriver driver, WebDriverWait wait) {
        if (instance == null) {
            instance = new OnboardingScreen(driver, wait);
        }
        return instance;
    }

    public void completedOnboarding() {
        waitAndClick(gotItButton);
        if (isElementPresentWithinTime(adressList,5)) {
            waitAndClick(takeMeToGmail);
        }
        waitAndClick(nextButton);
    }

    public void resetInstance() {
        instance = null;
    }
}
