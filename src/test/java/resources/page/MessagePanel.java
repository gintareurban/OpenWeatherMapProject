package resources.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.utils.Waiters;

public class MessagePanel extends AbstractPage{

    @FindBy(xpath = "//div[@class='panel-body']")
    private WebElement noticeMessage;


    public MessagePanel(WebDriver driver) {
        super(driver);
    }

    //getters
    public WebElement getNoticeMessage() {
        return noticeMessage;
    }

    public String getNoticeMessageText() {
        Waiters.waitForSingleElementVisibility(getNoticeMessage(), driver);
        return getNoticeMessage().getText();
    }

    public boolean verifySuccessfulSignIn() {
        return getNoticeMessageText().equals("Signed in successfully.");

    }

    public boolean verifyApiKeyCreated() {
        return getNoticeMessageText().equals("API key was created successfully");
    }

    public boolean verifyApiKeyEdited() {
        return getNoticeMessageText().equals("API key was edited successfully");
    }
}
