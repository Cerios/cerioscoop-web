package nl.cerios.testutil;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SeleniumTest extends MySqlDatabaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(SeleniumTest.class);

	public static final String BASE_URL = "http://localhost:9080/cerioscoop-web";

	private static WebDriver webDriver;

	public static WebDriver getWebDriver() {
		return webDriver;
	}

	/**
	 * This method creates the web driver that is required for Selenium tests.
	 * These tests will be skipped (without failing) if no driver was installed.
	 * They will also be skipped if the web server was not running.
	 */
	@BeforeClass
	public static void initWebDriver() {
		// Skip Selenium tests if the web server is not running.
		Assume.assumeTrue(isWebServerRunning());

		if (webDriver == null) {
			try {
				// Create driver to communicate with the (Chrome!) browser
				webDriver = new ChromeDriver();
				webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			} catch (IllegalStateException e) {
				LOG.error("Error creating WebDriver", e);
				quitWebDriver();
			}
		}

		// Skip Selenium tests if no web driver is available.
		Assume.assumeNotNull(webDriver);
	}

	@AfterClass
	public static void quitWebDriver() {
		if (webDriver != null) {
			webDriver.quit();
			webDriver = null;
		}
	}

	public static boolean isWebServerRunning() {
		try {
			// Test if the web server is running and the home page can be
			// reached.
			new URL(BASE_URL).openConnection().connect();
			return true;
		} catch (IOException e) {
			LOG.error("The URL '" + BASE_URL + "' cannot be accessed. Please check if the web server is running.", e);
			return false;
		}
	}
}