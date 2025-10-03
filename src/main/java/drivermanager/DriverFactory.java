package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class DriverFactory {
	
WebDriver driver = null;
	
	public static ThreadLocal<WebDriver> mydriver = new ThreadLocal<>();
	public WebDriver launchBrowser(String browser)
	{
		if(browser.equals("Chrome"))
		{
			mydriver.set(new ChromeDriver());
		}
		else if(browser.equals("Firefox"))
		{
			mydriver.set(new FirefoxDriver());
		}
		else if(browser.equals("EdgeDriver"))
		{
			mydriver.set(new EdgeDriver());
		}
		else 
		{
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver()
	{
		return mydriver.get();
	}

}
