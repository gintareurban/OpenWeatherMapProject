package resources.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.utils.Waiters;

public class EditApiKeyNameModal extends AbstractPage {

    //inputs
    @FindBy(id = "edit_key_form_name")
    private WebElement inputEditApiKeyName;

    //buttons
    @FindBy(xpath = "//div[contains(@class, 'pop-up-footer')]/button[text()='Save']")
    private WebElement buttonSave;

    @FindBy(xpath = "//div[contains(@class, 'pop-up-footer')]/button[text()='Cancel']")
    private WebElement buttonCancel;

    public EditApiKeyNameModal(WebDriver driver) {
        super(driver);
    }

    //getters
    public WebElement getInputEditApiKeyName() {
        return inputEditApiKeyName;
    }

    public WebElement getButtonSave() {
        return buttonSave;
    }

    public WebElement getButtonCancel() {
        return buttonCancel;
    }

    public void enterInputEditApiKeyName(String apiKeyName) {
        Waiters.waitForClickable(getInputEditApiKeyName(), driver);
        getInputEditApiKeyName().clear();
        getInputEditApiKeyName().sendKeys(apiKeyName);
    }

    public void clickButtonSave() {
        Waiters.waitForClickable(getButtonSave(), driver);
        getButtonSave().click();
    }

    public void clickButtonCancel() {
        Waiters.waitForClickable(getButtonCancel(), driver);
        getButtonCancel().click();
    }

    public void enterInputEditApiKeyNameAndSave(String apiKeyName) {
        enterInputEditApiKeyName(apiKeyName);
        clickButtonSave();
    }
}
