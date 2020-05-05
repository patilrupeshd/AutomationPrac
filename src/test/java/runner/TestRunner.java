package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "stepdef", "pages", "utility" }, plugin = {
		"json:target/cucumber.json" }, tags = "@login+ve", strict = true, monochrome = true)
public class TestRunner {

}
