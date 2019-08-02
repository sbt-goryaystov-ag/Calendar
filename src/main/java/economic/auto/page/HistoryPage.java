package economic.auto.page;

import economic.auto.core.ElementTitle;
import economic.auto.core.PageTitle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageTitle("Детальная информация")
public class HistoryPage extends BasePage {
    public HistoryPage(){
        waitUntilVisible(country);
    }

    @ElementTitle("страна")
    @FindBy(xpath = "//span[contains(text(),'Country:')]/parent::div//a")
    public WebElement country;

    @ElementTitle("приоритет")
    @FindBy(xpath = "//td[@id='actualValueDate']/parent::tr/td[2]")
    public WebElement priority;

    @ElementTitle("история")
    @FindBy(xpath = "//li[@data-content='history']")
    public WebElement history;
}