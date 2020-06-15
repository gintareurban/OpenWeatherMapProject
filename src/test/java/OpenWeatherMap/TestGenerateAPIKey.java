package OpenWeatherMap;

import org.testng.annotations.Test;
import resources.test.BaseTestUI;
import resources.utils.FileWriterTxtUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestGenerateAPIKey extends BaseTestUI {

    @Test(priority = 1, groups = { "generateApiKey" })
    public void testGenerateAPIKey() {

        openWebsite();
        login();
        assertThat("Login was not successful", messagePanel.verifySuccessfulSignIn(), is(true));


        navPage.clickLinkAPIKeysTab();

        StringBuilder builder = new StringBuilder();
        new Random().ints(5,1,10).forEach(builder::append);
        String apiKeyNameInitial = "TestKey" + builder.toString();

        apiKeysTab.generateApiKey(apiKeyNameInitial);
        int generatedKeyRowNumber = apiKeysTab.getTableRowNumberByCellValueInColumn("Name", apiKeyNameInitial);
        assertThat("Row with Api Key Name cannot be found in the table", generatedKeyRowNumber, is(greaterThan(0)));

        String generatedKey = apiKeysTab.getTableColumnKeyCellValueByRowNumber(generatedKeyRowNumber);


        apiKeysTab.clickEditKeyNameByRowNumber(generatedKeyRowNumber);
        String apiKeyNameUpdated = "New" + apiKeyNameInitial;

        editApiKeyNameModal.enterInputEditApiKeyNameAndSave(apiKeyNameUpdated);
        int updatedKeyRowNumber = apiKeysTab.getTableRowNumberByCellValueInColumn("Key", generatedKey);
        softly.assertThat(apiKeysTab.getTableColumnNameCellValueByRowNumber(updatedKeyRowNumber)).as("New key name was not updated correctly").isEqualTo(apiKeyNameUpdated);
        softly.assertThat(messagePanel.verifyApiKeyEdited()).as("Successful key edit message not displayed").isTrue();
        softly.assertAll();


        String filePath = "src/test/java/resources/temp/apiKey.txt";
        try {
            FileWriterTxtUtils.writeDataToFile(filePath, Collections.singletonList(generatedKey));
        } catch (IOException e) {
            System.out.println("Could not write to file");
            e.printStackTrace();
        }
        File apiKeyFile = new File(filePath);
        assertThat("File does not exists", apiKeyFile.exists(), is(true));

    }
}
