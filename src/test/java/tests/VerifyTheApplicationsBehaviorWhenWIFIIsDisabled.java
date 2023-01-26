package tests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import utils.JsonParser;
import utils.RunnerExtension;
import utils.Utils;

@ExtendWith(RunnerExtension.class)
public class VerifyTheApplicationsBehaviorWhenWIFIIsDisabled extends BaseTest {

    @BeforeEach
    public void setUp() {
        Utils.turnOnAllNetworkConnection();
    }

    @Test
    @Description("Case -3 Verify the application’s behavior when WIFI is disabled ")
    public void verifyTheApplicationsBehaviorWhenWIFIIsDisabledTest() throws InterruptedException {
        onboardingScreen.completedOnboarding();
        initialGmailScreen.navigateToNewEmailPage();

        Utils.turnOffAllNetworkConnection();
        newEmailScreen.sendEmail(JsonParser.getTo(), subject, body);
        Assert.assertTrue("Label 'Queued' is not displayed", initialGmailScreen.validateQueuedEmail());

        Utils.turnOnAllNetworkConnection();

        Assert.assertFalse("Label 'Queued' is not disappeared", initialGmailScreen.validateQueuedEmail());
    }

    @AfterEach
    public void tearDown() {
        initialGmailScreen.deleteEmail();
    }
}
