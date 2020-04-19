package runner;

import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utility.BrowserUtil;

import org.junit.runner.RunWith;
import org.openqa.selenium.remote.BrowserType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",glue ="stepdef", strict = true, monochrome = true)
public class TestRunner {

}
