package screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InitialGmailScreen extends BaseScreen {
    AndroidDriver driver;
    private static InitialGmailScreen instance;

    @FindBy(id = "compose_button")
    private MobileElement addEmailButton;
    @FindBy(xpath = "//*[contains(@text,'Unread, , , me')]")
    private MobileElement emailItem;
    @FindBy(id = "delete")
    private MobileElement deleteButton;
    @FindBy(xpath = "//*[contains(@text,'Queued')]")
    private MobileElement queuedLabel;

    private final By NAVIGATION_DRAWER = MobileBy.AccessibilityId("Open navigation drawer");
    private final By TRASH_FOLDER = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Trash\")");
    private final String xpathPattern = "//*[contains(@text,'%s, %s,')]";

    int hour;
    int minute;

    public InitialGmailScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
    }

    public static InitialGmailScreen getInitialGmailScreen(AndroidDriver driver, WebDriverWait wait) {
        if (instance == null) {
            instance = new InitialGmailScreen(driver, wait);
        }
        return instance;
    }

    public void navigateToNewEmailPage() {
        waitAndClick(addEmailButton);
    }

    public void deleteEmail() {
        if (isElementPresentWithinTime(emailItem, 5)) {
            longTapping(emailItem);
            waitAndClick(deleteButton);
        }
    }

    public boolean validationIsEmailReceived(String subject, String body) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        LocalTime timeL = LocalTime.parse(driver.getDeviceTime(), dtf);

        hour = timeL.getHour();
        minute = timeL.getMinute();
        return validationEmail(subject, body);
    }

    public boolean validateQueuedEmail() {
        return isElementPresentWithinTime(queuedLabel, 30);
    }

    public boolean validationIsDeletedEmailInTrash(String subject, String body) {
        waitAndClick(NAVIGATION_DRAWER);
        waitAndClick(TRASH_FOLDER);
        return validationEmail(subject, body);
    }

    public boolean validationEmailIsDeleted(String subject, String body) {
        return validationEmail(subject, body);
    }

    private boolean validationEmail(String subject, String body) {
        if (isElementFoundByLocator(By.xpath(String.format(xpathPattern, subject, body)))) {
            String text = findElementByWithWait(By.xpath(String.format(xpathPattern, subject, body))).getAttribute("text");
            return text.contains(Utils.formatHours(hour)) && text.contains(String.valueOf(minute)) || text.contains(String.valueOf(minute + 1));
        } else return false;
    }

    public void resetInstance() {
        instance = null;
    }
}
