package driver;

import constant.ConstatntEnv;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.PLATFORM_NAME;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;


public class Driver {

    public static AndroidDriver getDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PLATFORM_NAME, "Android");
        caps.setCapability(AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(UDID, "emulator-5554");
        caps.setCapability(NO_RESET, "false");
        caps.setCapability(APP_ACTIVITY, ".ConversationListActivityGmail");
        caps.setCapability(APP_PACKAGE, "com.google.android.gm");
        caps.setCapability(AVD, "Pixel_4_API_30");
        caps.setCapability(AVD_LAUNCH_TIMEOUT, "1000000");
        caps.setCapability(AVD_READY_TIMEOUT, "1000000");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);

        return new AndroidDriver(new URL(ConstatntEnv.URL_NAME), caps);
    }

    public static void closeDriver(AndroidDriver driver) {
        System.out.print("close(): ");
        if (driver != null) {
            driver.quit();
        }
    }
}
