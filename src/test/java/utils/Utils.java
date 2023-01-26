package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

public class Utils extends BaseTest {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static String formatHours(int hour) {
        if (hour > 12) {
            return String.valueOf(hour - 12);
        } else return String.valueOf(hour);
    }

    public static void turnOffAllNetworkConnection() {
        long bitMask = driver.getConnection().getBitMask();
        if (bitMask == 6) {
            driver.toggleWifi();
            driver.toggleData();
        } else if (bitMask == 2) {
            driver.toggleWifi();
        } else if (bitMask == 4) {
            driver.toggleData();
        }
    }

    public static void turnOnAllNetworkConnection() {
        long bitMask = driver.getConnection().getBitMask();
        if (bitMask == 0) {
            driver.toggleWifi();
            driver.toggleData();
        } else if (bitMask == 2) {
            driver.toggleData();
        } else if (bitMask == 4) {
            driver.toggleWifi();
        }
    }
}
