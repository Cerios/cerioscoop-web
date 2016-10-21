package nl.cerios.cerioscoop.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import nl.cerios.testutil.SeleniumTest;

/**
 * This class is just a hook for the Cucumber tests to be executed. It extend
 * class SeleniumTest, so all Cucumber tests are skipped (without failing) when
 * the requirements for Selenium tests are not met, i.e. the web server is not
 * running or the web driver is not installed).
 * 
 * We will store the reports in the target map.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/html/", "json:target/json/output.json"},  //Report test results
		features = { "src/test/resources/features/" },
		tags = {"@MoviePresentation, @Buytickets"}
		)
public class RunnerTest extends SeleniumTest{
	
}
