package tests;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.RunnerExtension;

@ExtendWith(RunnerExtension.class)
public class ValidateAbilityToDeleteReceivedEmail extends BaseTest {

    @Test
    @Description("Case -2 Validate ability to delete received email")
    public void validateAbilityToDeleteReceivedEmailTest() throws InterruptedException {
        sendMessage();
        Assert.assertTrue(initialGmailScreen.validationIsEmailReceived(subject, body));

        initialGmailScreen.deleteEmail();

        Assert.assertFalse("Email was not deleted", initialGmailScreen.validationEmailIsDeleted(subject, body));
        Assert.assertTrue("Email is not in Trash folder", initialGmailScreen.validationIsDeletedEmailInTrash(subject, body));

    }
}
