package resources.test;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import resources.page.*;
import resources.utils.FileReaderUtils;

import java.util.Properties;

public class BaseTestUI extends AbstractTest {

    protected static LoginPage login;
    protected static HeaderPage header;
    protected static MessagePanel messagePanel;
    protected static NavPage navPage;
    protected static ApiKeysTab apiKeysTab;
    protected static EditApiKeyNameModal editApiKeyNameModal;
    protected static SoftAssertions softly;

    @BeforeClass
    public static void preconditions() {
        login = new LoginPage(driver);
        header = new HeaderPage(driver);
        messagePanel = new MessagePanel(driver);
        navPage = new NavPage(driver);
        apiKeysTab = new ApiKeysTab(driver);
        editApiKeyNameModal = new EditApiKeyNameModal(driver);
        softly = new SoftAssertions();
    }

    protected static void openWebsite() {
        driver.get("https://openweathermap.org/");
    }

    protected static void login() {
        header.clickLinkSignIn();
        Properties prop = FileReaderUtils.readPropertiesFile("src/test/java/resources/testData/local_login.properties");
        login.enterUsernamePasswordAndLogin(prop.getProperty("email"), prop.getProperty("password"));
    }
}
