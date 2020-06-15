package resources.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.utils.Waiters;

public class LoginPage extends AbstractPage {
    //inputs
    @FindBy(id = "user_email")
    private WebElement inputUserName;

    @FindBy(id = "user_password")
    private WebElement inputUserPassword;

    //buttons
    @FindBy(xpath = "//form[@id='new_user']//input[@value='Submit']")
    private WebElement buttonSubmit;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //getters

    public WebElement getButtonSubmit() {
        return buttonSubmit;
    }


    public WebElement getInputUserName() {
        return inputUserName;
    }


    public WebElement getInputUserPassword() {
        return inputUserPassword;
    }


    public void clickButtonSubmit() {
        Waiters.waitForClickable(getButtonSubmit(), driver);
        getButtonSubmit().click();
    }

    public void enterInputUserName(String username) {
        Waiters.waitForClickable(getInputUserName(), driver);
        getInputUserName().sendKeys(username);
    }

    public void enterInputUserPassword(String userPassword) {
        Waiters.waitForClickable(getInputUserPassword(), driver);
        getInputUserPassword().sendKeys(userPassword);
    }

    public void enterUsernamePasswordAndLogin(String username, String userPassword) {
        enterInputUserName(username);
        enterInputUserPassword(userPassword);
        clickButtonSubmit();
    }



}
