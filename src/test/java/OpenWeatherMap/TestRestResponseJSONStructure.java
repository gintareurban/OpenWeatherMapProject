package OpenWeatherMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.models.City;
import resources.test.BaseTestREST;
import resources.utils.FileReaderUtils;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestRestResponseJSONStructure extends BaseTestREST {

    @BeforeClass
    public void setup() {
        waitForApiKeyToActivate(apiKey);
    }

    @DataProvider(name = "validCityData")
    public static Object[] testDataValidCity() throws IOException {
        return FileReaderUtils.getCitiesFromXml("src/test/java/resources/testData/validCityData.xml");
    }

    @Test(priority = 6, groups = { "testAPI" }, dataProvider = "validCityData")
    public void testVerifyResponseJSONStructure(City city) throws IOException {

        Response response = RestAssured.get(endPointById + apiParam, city.getId(), apiKey);
        JsonNode data = JsonLoader.fromString(response.getBody().asString());
        JsonNode schema = JsonLoader.fromFile(new File("src/test/java/resources/schema/schema.json"));

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonValidator validator = factory.getValidator();

        ProcessingReport report = null;
        try {
            report = validator.validate(schema, data);
        } catch (ProcessingException e) {
            e.printStackTrace();
        }
        assertThat("JSON validation failed for City: " + city.getName(), report.isSuccess(), is(true));
    }
}
