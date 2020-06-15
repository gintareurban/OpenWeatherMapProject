package OpenWeatherMap;

import org.testng.annotations.Test;
import resources.test.BaseTestREST;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestApiKeyActivationTime extends BaseTestREST {

    @Test(priority = 2, groups = { "testApiKeyActivateTime" })
    public void testApiKeyActivationTime() {

        long start = System.currentTimeMillis();

        waitForApiKeyToActivate(apiKey);

        long activationTimeMs = (System.currentTimeMillis() - start);
        long maxTime = 600L;
        assertThat(String.format("Api key does not activate in under %s seconds", maxTime), activationTimeMs/1000, is(lessThan(maxTime)));

    }
}
