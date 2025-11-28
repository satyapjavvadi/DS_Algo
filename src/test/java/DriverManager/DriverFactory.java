package DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static WebDriver driver;
	private static String browserName;

	public static void setBrowser(String browser) {
		browserName = browser;
	}

	public static WebDriver initDriver() {
		// String driverPath =
		// System.getProperty("C:\\Users\\prash\\github\\DS_Algo\\src\\test\\resources\\edgedriver_win64\\msedgedriver.exe")
		// + "/drivers/";
		if (browserName == null) {
			throw new RuntimeException(
					"Browser is not set! Make sure @Parameters(\"browser\") is passed in TestNG XML.");
		}

		switch (browserName.toLowerCase()) {

		case "chrome":
			driver = WebDriverManager.chromedriver().create();
			break;

		case "edge":

			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\prash\\github\\DS_Algo\\src\\test\\resources\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = WebDriverManager.firefoxdriver().create();
			break;

		default:
			throw new RuntimeException("Invalid browser: " + browserName);
		}

		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
