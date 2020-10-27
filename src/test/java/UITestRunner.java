import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/resources/cucumber/scenarios/ui",
        glue = {"uisteps"},
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber.html"})
public class UITestRunner extends AbstractTestNGCucumberTests {
}