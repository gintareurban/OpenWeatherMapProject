package resources.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    private WebDriverWait wait;

    public static void waitForSingleElementVisibility(WebElement element, WebDriver driver) {
        getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickable(WebElement element, WebDriver driver) {
        waitForSingleElementVisibility(element,driver);
        getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }


    public static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, 10);
    }
}
