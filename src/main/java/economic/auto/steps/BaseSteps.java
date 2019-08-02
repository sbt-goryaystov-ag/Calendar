package economic.auto.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import economic.auto.util.Init;
import economic.auto.util.TestProperties;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 21.01.2019.
 */
public class BaseSteps {

    private static WebDriver driver;
    public static final Logger LOG = Logger.getLogger(BaseSteps.class);
    @Before
    public void setUp() throws Exception {

        String browser = TestProperties.getInstance().getProperties("browser");
        Assert.assertFalse("Не указан браузер в переменной browser!", null == browser);
        switch (browser){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver", TestProperties.getInstance().getProperties("webdriver.chrome.driver"));
                options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1309.0 Safari/537.17");
                options.addArguments("start-maximized");
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setBrowserName("chrome");
                caps.setPlatform(Platform.WINDOWS);
                caps.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(caps);
                Init.setDriver(driver);
                LOG.info("Выбран браузер chrome");
                break;
            default:
                throw new AssertionError();
        }

        String baseUrl = TestProperties.getInstance().getProperties("appUrl");
        Assert.assertFalse("Не указан url!", null == baseUrl);
        LOG.info("Открываем браузер по следующиму url - '" + baseUrl + "'");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @After
    public static void tearDown() throws Exception {
        driver.quit();
    }
}