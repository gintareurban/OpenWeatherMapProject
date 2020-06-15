package OpenWeatherMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.models.City;
import resources.test.BaseTestREST;
import resources.utils.FileReaderUtils;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

public class TestApiCallsPositiveFlows extends BaseTestREST {

    @BeforeClass
    public void setup() {
        waitForApiKeyToActivate(apiKey);
    }

    @DataProvider(name = "validCityData")
    public static Object[] testDataValidCity() throws IOException {
        return FileReaderUtils.getCitiesFromXml("src/test/java/resources/testData/validCityData.xml");
    }

    @Test(priority = 3, groups = { "testAPI" }, dataProvider = "validCityData")
    public void testVerifyCityDataById(City city) {

        Response response = RestAssured.get(endPointById + apiParam, city.getId(), apiKey);

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("name", equalTo(city.getName()))
                .assertThat().body("sys.country", equalTo(city.getCountryCode()))
                .assertThat().body("coord.lon", equalTo(Float.parseFloat(city.getLongitude())))
                .assertThat().body("coord.lat", equalTo(Float.parseFloat(city.getLatitude())));

    }

    @Test(priority = 4, groups = { "testAPI" }, dataProvider = "validCityData")
    public void testVerifyCityDataByCoordinates(City city) {

        Response response = RestAssured.get(endPointByCoord + apiParam, city.getLatitude(), city.getLongitude(), apiKey);

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("id", equalTo(Integer.parseInt(city.getId())))
                .assertThat().body("name", equalTo(city.getName()))
                .assertThat().body("sys.country", equalTo(city.getCountryCode()));

    }

    @Test(priority = 5, groups = { "testAPI" }, dataProvider = "validCityData")
    public void testVerifyCityDataByZipCode(City city) {

        Response response = RestAssured.get(endPointByZipCode + apiParam, city.getZipCode(), city.getCountryCode(), apiKey);

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("name", containsString(city.getName()))
                .assertThat().body("sys.country", equalTo(city.getCountryCode()))
                .assertThat().body("coord.lon", equalTo(Float.parseFloat(city.getLongitude())))
                .assertThat().body("coord.lat", equalTo(Float.parseFloat(city.getLatitude())));

    }
}
