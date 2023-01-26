package screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BaseScreen {

    AndroidDriver driver;
    WebDriverWait wait;

    BaseScreen(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected boolean isElementChecked(MobileElement element) {
        return element.isSelected();
    }

    protected boolean isElementPresentWithinTime(WebElement element, long time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private ExpectedCondition<MobileElement> elementIdDisplayed(By by) {
        return driver -> {
            List<MobileElement> listMobileElemets;
            listMobileElemets = driver.findElements(by);
            if (listMobileElemets.size() > 0 && listMobileElemets.get(0).isDisplayed()) {
                return listMobileElemets.get(0);
            } else return null;
        };
    }

    protected MobileElement findElementByWithWait(By by) {
        return wait.until(elementIdDisplayed(by));
    }

    public void longTapping(MobileElement element) {
        Dimension eventElementSize = element.getSize();
        int y = element.getCenter().getY();
        int X = (int) (0.5 * eventElementSize.getWidth());

        new TouchAction(driver)
                .longPress(PointOption.point(X, y))
                .release()
                .perform();
    }

    public void tapElement(MobileElement element) {
        Dimension eventElementSize = element.getSize();
        int y = element.getCenter().getY();
        int x = (int) (0.5 * eventElementSize.getWidth());

        new TouchAction(driver)
                .tap(PointOption.point(x, y))
                .release()
                .perform();
    }

    public void waitAndClick(By by) {
        findElementByWithWait(by).click();
    }

    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected boolean isElementFoundByLocator(By elementLocator) {
        return driver.findElements(elementLocator).size() > 0;
    }

    protected void clearAndEnterTextToInput(MobileElement input, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();
        input.sendKeys(text);
    }
}
