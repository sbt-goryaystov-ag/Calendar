package economic.auto.page;

import economic.auto.core.ElementTitle;
import economic.auto.util.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Init.getDriver(), this);
    }

    public WebElement getElementByTitle(String title) {
        Field field = Arrays.stream(this.getClass().getFields())
                .filter(f -> f.getType().equals(WebElement.class))
                .filter(f -> f.getAnnotation(ElementTitle.class) != null)
                .filter(f -> f.getAnnotation(ElementTitle.class).value().equalsIgnoreCase(title))
                .findFirst().orElseThrow(() -> new RuntimeException("Не найден элемент с названием " + title));
        field.setAccessible(true);
        WebElement element = null;
        try {
            element = (WebElement) field.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return element;
    }

    void waitUntilVisible(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(Init.getDriver(), 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void check(String id, String value) {
        List<WebElement> elements = Init.getDriver().findElements(By.xpath("//ul[@id='" + id + "']/li/label"));
        WebElement web2;
        for (WebElement web : elements) {
            scrollToElement(web);
            String text = web.getText();
            web2 = web.findElement(By.xpath(".//parent::li/input"));
            if (value.equalsIgnoreCase(text)) {
                if (!web2.isSelected()) {
                    web2.click();
                }
            } else {
                if (web2.isSelected()) {
                    web.click();
                }
            }
        }
    }

    public static void clickField(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    public static void clickFirstElement(String currency) {
        try {
            Thread.sleep(2000);
            WebElement element = (new WebDriverWait(Init.getDriver(), 10)
                    .until(ExpectedConditions.elementToBeClickable(Init.getDriver().findElement(By.xpath("//div[@id='economicCalendarTable']//div[contains(text(),'" + currency + "')]/ancestor::div[3]//a")))));
            scrollToElement(element);
            element.click();
        } catch (Exception e) {
            throw new AssertionError();
        }

    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Init.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
    }
}