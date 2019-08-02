package economic.auto.util;

import economic.auto.core.PageTitle;
import economic.auto.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;


public class Init {

    private static WebDriver driver;
    private static BasePage currentPage;
    private static Set<Class<? extends BasePage>> allpages;

    static {
        Reflections reflections = new Reflections("economic.auto");
        allpages = reflections.getSubTypesOf(BasePage.class);
    }

    public static void setupPage(String title) {
        currentPage = findPageByTitle(title);
    }

    private static BasePage findPageByTitle(String title) {
        Class<?extends BasePage> pageClass = allpages.stream()
                .filter(page -> page.getAnnotation(PageTitle.class).value().equals(title))
                .findAny().orElseThrow(() -> new RuntimeException("Не найдена страница с названием " + title));
        BasePage foundPage = null;
        try {
            foundPage = pageClass.getConstructor().newInstance();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
        return foundPage;
    }

    public static BasePage getCurrentPage(){
        return currentPage;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Init.driver = driver;
    }
}