import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by user on 11.03.2019.
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/"}, glue = {"economic.auto.steps"},
plugin = {"economic.auto.util.AllureReporter",},
tags = {"@start"})
public class CucumberRunner {
}
