package economic.auto.page;

import economic.auto.core.ElementTitle;
import economic.auto.core.PageTitle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageTitle("Страница календаря")
public class CalendarPage extends BasePage {

    @ElementTitle("текущий месяц")
    @FindBy(xpath = "//input[@id='filterDate4']/parent::li/label")
    public WebElement currentMonth;
}