package resources.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import resources.utils.CheckOS;

public abstract class AbstractTest {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(ITestContext context) {
        CheckOS.setWebDriverPropertiesByOS();
        driver = new ChromeDriver();
        context.setAttribute("WebDriver", driver);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
