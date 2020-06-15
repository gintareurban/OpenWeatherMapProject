package resources.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.utils.Waiters;

public class HeaderPage extends AbstractPage {

    //links
    @FindBy(xpath = "//ul[@class='header-website']/li[@class='user-sign-in']/a")
    private WebElement linkSignIn;


    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    //getters
    public WebElement getLinkSignIn() {
        return linkSignIn;
    }

    public void clickLinkSignIn() {
        Waiters.waitForClickable(getLinkSignIn(), driver);
        getLinkSignIn().click();
    }

}
