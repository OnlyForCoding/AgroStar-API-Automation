package runner;


import controller.CucumberManager;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"steps"},
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/automation-report/AgroStar-Automation-report.html" }
)
public class TestRunner extends AbstractTestNGCucumberTests {
    static {
        new CucumberManager();
    }
}
