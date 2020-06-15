package resources.utils;

public class CheckOS {

    public static void setWebDriverPropertiesByOS() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        } else if (os.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver_linux");
        };


    }


}
