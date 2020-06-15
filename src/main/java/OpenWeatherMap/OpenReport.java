package OpenWeatherMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import resources.utils.CheckOS;

public class OpenReport {
    public static void main(String[] args) {

        CheckOS.setWebDriverPropertiesByOS();
        WebDriver driver = new ChromeDriver();

        driver.get(String.format("file://%s/target/surefire-reports/emailable-report.html", System.getProperty("user.dir")));


    }
}
