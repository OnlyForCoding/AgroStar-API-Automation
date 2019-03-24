package controller;

import com.cucumber.listener.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;

public class CucumberManager {

    public CucumberManager() {
    }

    @BeforeClass
    public static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("target/automation-report/AgroStar-Automation-report.html");
    }

    @AfterClass
    public static void teardownn() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("Environment", System.getProperty("env"));
        Reporter.setSystemInfo("Browser", System.getProperty("browser"));
    }
}
