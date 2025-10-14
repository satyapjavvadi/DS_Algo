package pages;

import org.openqa.selenium.WebDriver;

import DriverManager.DriverFactory;

public class PageObjectManager {

    private final WebDriver driver;
    private LaunchPage launchPage;
    private RegisterPage registerPage;

	public PageObjectManager() {
		this.driver = DriverFactory.getDriver();
		if (this.driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}

	}

	public LaunchPage getLaunchpage() {
		if (launchPage == null) {
			launchPage = new LaunchPage(driver);
		}

        return launchPage;
    }
    
    public RegisterPage getregisterpage() {
        if(registerPage == null){
            registerPage = new RegisterPage(driver);
        }

        return registerPage;
    }
    
}
