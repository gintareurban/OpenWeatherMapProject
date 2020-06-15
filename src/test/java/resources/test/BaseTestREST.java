package resources.test;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.awaitility.Awaitility;
import org.testng.annotations.BeforeClass;
import resources.utils.FileReaderUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTestREST {
    protected static String apiKey;
    protected static String apiParam;
    protected static String endPointById;
    protected static String endPointByCoord;
    protected static String endPointByZipCode;
    protected static String endPointByCityName;

    @BeforeClass
    public static void preconditions() {
        RestAssured.baseURI = "http://api.openweathermap.org";
        apiParam = "&appid={apiKey}";
        endPointById = "/data/2.5/weather?id={city id}";
        endPointByCoord = "/data/2.5/weather?lat={lat}&lon={lon}";
        endPointByZipCode = "/data/2.5/weather?zip={zip code},{country code}";
        endPointByCityName = "/data/2.5/weather?q={city name}, {country code}";

        String filePath = "src/test/java/resources/temp/apiKey.txt";
        try {
            apiKey = FileReaderUtils.getTestDataFromTxt(filePath).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected static void waitForApiKeyToActivate(String key) {
        Awaitility.with()
                .pollInterval(15, TimeUnit.SECONDS)
                .pollDelay(0, TimeUnit.MILLISECONDS)
                .await()
                .atMost(20, TimeUnit.MINUTES)
                .until(() -> RestAssured.get(endPointByCityName + apiParam, "London", "uk", key).statusCode() == HttpStatus.SC_OK)
        ;
    }
}
