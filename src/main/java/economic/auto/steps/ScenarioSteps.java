package economic.auto.steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import economic.auto.page.BasePage;
import economic.auto.util.Init;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс шагов для фичи
 */
public class ScenarioSteps {
    public static final Logger LOG = Logger.getLogger(ScenarioSteps.class);

    @Step("Пользователь находится на странице {0}")
    @Тогда("(?:пользователь находится на странице|открывается страница) \"(.*)\"")
    public void openPage(String page) {
        Init.setupPage(page);
    }

    @Step("Переходим на вкладку {0}")
    @Когда("^кликает на вкладку \"(.*)\"")
    public void clickField(String fieldTitle) {
        LOG.info("Открываем вкладку '" + fieldTitle + "'");
        Init.getCurrentPage().getElementByTitle(fieldTitle).click();
    }

    @Step("Выбран период {0}")
    @Когда("^выбран период \"(.+)\"$")
    public void stepSelectPeriod(String period) {
        LOG.info("Отфильтровываем по периоду '" + period + "'");
        BasePage.clickField(Init.getCurrentPage().getElementByTitle(period));
    }

    @Step("Выбран чекбокс важности {0}")
    @Когда("^выбран чекбокс важности \"(.+)\"$")
    public void stepSelectImportance(String importance) {
        LOG.info("Отмечаем чекбокс важности '" + importance + "'");
        BasePage.check("economicCalendarFilterImportance", importance);
    }

    @Step("Выбран чекбокс валюты {0}")
    @Когда("^выбран чекбокс валюты \"(.+)\"$")
    public void stepSelectСurrencies(String сurrencies) {
        LOG.info("Отмечаем чекбокс валюты '" + сurrencies + "'");
        BasePage.check("economicCalendarFilterCurrency", сurrencies);
    }

    @Step("Нажимаем на первое значение из таблицы по валюте {0}")
    @Когда("^нажимаем на первое значение из таблицы по валюте \"(.+)\"$")
    public void stepClickOnFirst(String сurrency) {
        LOG.info("Нажимаем на ссылку первого значения из таблицы по валюте '" + сurrency + "'");
        BasePage.clickFirstElement(сurrency);
    }

    @Step("Проверяем текст {0} у элемента {1}")
    @Тогда("^проверяем текст \"(.+)\" у элемента \"(.+)\"$")
    public void stepCompareText(String text, String element) {
        LOG.info("Проверяем текст '" + text + "' у элемента '" + element + "'");
        Assert.assertEquals(text.toLowerCase(), Init.getCurrentPage().getElementByTitle(element).getText().toLowerCase());
        LOG.info("Текст соответствует");
    }

    @Тогда("^получаем в лог историю события за последние (.+) месяц(|ев|а)$")
    public void stepGetLog(int count, String type){
        StringBuilder sb = new StringBuilder();
        sb.append("Результаты истории:\n");
        sb.append(" | Date         | Actual | Forecast | Previous | \n");
        for (int i = 1; i <= count; i++){
            for(int j = 1; j <= 5; j++){
                if(j == 2){
                    continue;
                }
                String elem = new WebDriverWait(Init.getDriver(), 10)
                        .until(ExpectedConditions.elementToBeClickable(Init.getDriver().findElement(By.xpath("//div[@id='eventHistoryContent']/div/div[" + i + "]/div[" + j + "]")))).getText();
                sb.append(StringUtils.center(" | " + elem + ((elem.contains("-") || elem.length() > 10)  ? " " : "  "), elem.length() + 2));
            }
            sb.append(" |");
            sb.append("\n");
        }
        LOG.info(sb.toString());
    }
}