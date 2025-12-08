package DriverManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public static ThreadLocal<WebDriver> mydriver = new ThreadLocal<>();

	public static WebDriver launchBrowser(String browser) {

		if (browser.equalsIgnoreCase("Chrome")) {
			mydriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("Firefox")) {
			mydriver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("Edge")) {
			mydriver.set(new EdgeDriver());
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return getDriver();

	}

	// adds thread safety for concurrent access(ThreadLocal itself is thread-safe,
	// this can help avoid race conditions in some setups
	public static synchronized WebDriver getDriver() {
		return mydriver.get();
	}

}