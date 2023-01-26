package screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewEmailScreen extends BaseScreen {
    private static NewEmailScreen instance;

    @FindBy(xpath = "//*[@resource-id='com.google.android.gm:id/peoplekit_autocomplete_chip_group']//android.widget.EditText")
    private MobileElement toInput;
    @FindBy(id = "subject")
    private MobileElement subjectInput;
    @FindBy(id = "send")
    private MobileElement sendButton;
    @FindBy(id = "android:id/button1")
    private MobileElement gotItButton;
    @FindBy(id = "peoplekit_listview_contact_name")
    private MobileElement contactNameItem;

    private final By BODY_INPUT = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Compose email\")");

    public NewEmailScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
    }

    public static NewEmailScreen getNewEmailScreen(AndroidDriver driver, WebDriverWait wait) {
        if (instance == null) {
            instance = new NewEmailScreen(driver, wait);
        }
        return instance;
    }

    public void fillEmail(String to, String subject, String body) {
        clearAndEnterTextToInput(toInput, to);
        waitAndClick(contactNameItem);
        clearAndEnterTextToInput(subjectInput, subject);
        clearAndEnterTextToInput(findElementByWithWait(BODY_INPUT), body);
    }

    public void sendEmail(String to, String subject, String body) {
        if (isElementPresentWithinTime(gotItButton, 15)) {
            waitAndClick(gotItButton);
        }
        fillEmail(to, subject, body);
        waitAndClick(sendButton);
    }

    public void resetInstance() {
        instance = null;
    }
}
