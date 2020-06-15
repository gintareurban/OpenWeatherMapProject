package resources.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.utils.Waiters;

public class NavPage extends AbstractPage {

    //links
    @FindBy(xpath = "//ul[@id='myTab']/li/a[text()='API keys']")
    private WebElement linkAPIKeysTab;


    public NavPage(WebDriver driver) {
        super(driver);
    }

    //getters
    public WebElement getLinkSignIn() {
        return linkAPIKeysTab;
    }

    public void clickLinkAPIKeysTab() {
        Waiters.waitForClickable(getLinkSignIn(), driver);
        getLinkSignIn().click();
    }

}
