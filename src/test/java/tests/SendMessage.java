package tests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.RunnerExtension;

@ExtendWith(RunnerExtension.class)
public class SendMessage extends BaseTest{

    @Test
    @Description("Case-1 Validate ability to send the email")
    public void sendMessageTest() {
        sendMessage();

        Assert.assertTrue(initialGmailScreen.validationIsEmailReceived(subject, body));
    }

    @AfterEach
    public void tearDown() {
        initialGmailScreen.deleteEmail();
    }
}
