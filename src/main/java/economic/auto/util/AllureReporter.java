package economic.auto.util;

import gherkin.formatter.model.Result;
import io.qameta.allure.cucumberjvm.AllureCucumberJvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

public class AllureReporter extends AllureCucumberJvm {

    @Override
    public void result(Result result) {
        if ("failed".equals(result.getStatus())) takeScreenshot();
        super.result(result);
    }


    @Attachment(type = "image/png", value = "Screenshot")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) Init.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
