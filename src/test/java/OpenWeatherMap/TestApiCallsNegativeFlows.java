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

import static org.hamcrest.Matchers.equalTo;

public class TestApiCallsNegativeFlows extends BaseTestREST {

    @BeforeClass
    public void setup() {
        waitForApiKeyToActivate(apiKey);
    }


    @DataProvider(name = "invalidCityData")
    public static Object[] testDataInvalidCity() throws IOException {
        return FileReaderUtils.getCitiesFromXml("src/test/java/resources/testData/invalidCityData.xml");
    }

    @Test(priority = 7, groups = { "testAPI" }, dataProvider = "invalidCityData")
    public void testVerifyIncorrectCityIdErrorResponses(City city) {

        Response response = RestAssured.get(endPointById + apiParam, city.getId(), apiKey);

        switch(city.getCategory()) {
            case "invalidCityId":
                response.then()
                        .statusCode(HttpStatus.SC_NOT_FOUND)
                        .assertThat().body("message", equalTo("city not found"));
                break;
            case "emptyCityId":
                response.then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .assertThat().body("message", equalTo("Nothing to geocode"));
                break;
            case "nullCityId":
                response.then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .assertThat().body("message", equalTo("null is not a city ID"));
                break;
            default:
                System.out.println(city.getCategory() + " category did not match any cases");
        }
    }

    @Test(priority = 8, groups = { "testAPI" })
    public void testVerifyIncorrectApiKeyErrorResponses() {

        Response response = RestAssured.get(endPointById + apiParam,"2643743", "");
        verifyUnAuthorisedRequest(response);

        response = RestAssured.get(endPointById + apiParam,"2643743", "null");
        verifyUnAuthorisedRequest(response);

        response = RestAssured.get(endPointById + apiParam,"2643743", "11111111111111111111111111111111");
        verifyUnAuthorisedRequest(response);

    }

    private void verifyUnAuthorisedRequest(Response response) {
        response.then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat().body("message", equalTo("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."));
    }
}
