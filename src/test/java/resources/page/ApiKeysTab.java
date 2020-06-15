package resources.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.utils.Waiters;

import java.util.List;

public class ApiKeysTab extends AbstractPage {

    //table
    @FindBy(xpath = "//table[@class='material_table api-keys']")
    private WebElement tableApiKeys;

    //inputs
    @FindBy(id = "api_key_form_name")
    private WebElement inputApiKeyName;

    //buttons
    @FindBy(xpath = "//form[@id='new_api_key_form']/input[@value='Generate']")
    private WebElement buttonGenerate;

    public ApiKeysTab(WebDriver driver) {
        super(driver);
    }

    //getters
    public WebElement getTableApiKeys() {
        return tableApiKeys;
    }

    public WebElement getInputApiKeyName() {
        return inputApiKeyName;
    }

    public WebElement getButtonGenerate() {
        return buttonGenerate;
    }

    public void enterInputApiKeyName(String apiKeyName) {
        Waiters.waitForClickable(getInputApiKeyName(), driver);
        getInputApiKeyName().sendKeys(apiKeyName);
    }

    public void clickButtonGenerate() {
        Waiters.waitForClickable(getButtonGenerate(), driver);
        getButtonGenerate().click();
    }

    public void generateApiKey(String apiKeyName) {
        enterInputApiKeyName(apiKeyName);
        clickButtonGenerate();
    }

    //table methods
    public List<WebElement> getTableRows() {
        Waiters.waitForSingleElementVisibility(getTableApiKeys(), driver);
        return getTableApiKeys().findElements(By.xpath("./tbody/tr"));
    }

    public List<WebElement> getTableHeader() {
        Waiters.waitForSingleElementVisibility(getTableApiKeys(), driver);
        return getTableApiKeys().findElements(By.xpath("./thead/tr/th"));
    }

    public WebElement getTableRowByRowNumber(int rowNumber) {
        return getTableRows().get(rowNumber - 1);
    }

    public WebElement getTableCellByColumnAndRowNumber(int columnNumber, int rowNumber) {
        return getTableRowByRowNumber(rowNumber).findElement(By.xpath("./td["+ columnNumber +"]"));
    }

    public int getTableColumnNumberByColumnName(String columnName) {
        for (int i = 0; i < getTableHeader().size(); i++) {
            if (columnName.equals(getTableHeader().get(i).getText())) {
                return i+1;
            }
        }
        return -1;
    }

    public String getTableColumnNameCellValueByRowNumber(int rowNumber) {
        return getTableCellByColumnAndRowNumber(getTableColumnNumberByColumnName("Name"), rowNumber).getText();
    }

    public String getTableColumnKeyCellValueByRowNumber(int rowNumber) {
        return getTableCellByColumnAndRowNumber(getTableColumnNumberByColumnName("Key"), rowNumber).findElement(By.xpath("./pre")).getText();
    }

    public void clickEditKeyNameByRowNumber(int rowNumber) {
        getTableCellByColumnAndRowNumber(getTableColumnNumberByColumnName(""), rowNumber).findElement(By.xpath("./a[i[contains(@class,'fa-edit')]]")).click();
    }

    public void clickRemoveKeyByRowNumber(int rowNumber) {
        getTableCellByColumnAndRowNumber(getTableColumnNumberByColumnName(""), rowNumber).findElement(By.xpath("./a[i[contains(@class,'fa-remove')]]")).click();
    }

    public int getTableRowNumberByCellValueInColumn(String columnName, String cellValue) {
        for (int i = 0; i < getTableRows().size(); i++) {
            if ((columnName.equals("Key") && cellValue.equals(getTableColumnKeyCellValueByRowNumber(i+1))
                    || (columnName.equals("Name") && cellValue.equals(getTableColumnNameCellValueByRowNumber(i+1))))) {
                return i + 1;
            }
        }
        return -1;
    }

}
