package pages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	private final WebDriver driver;
	
	private LaunchPage launchPage;
	private LoginPage loginPage;
    private RegisterPage registerPage;
    private LinkedListPage linkedlistPage;
    private StackPage stackPage;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
		if (this.driver == null) {
			throw new IllegalStateException("WebDriver is not initialized");
		}

	}

	public LaunchPage getLaunchPage() {
		if (launchPage == null) {
			launchPage = new LaunchPage(driver);
		}

		return launchPage;
	}
	
	public LoginPage getLoginPage()
	{
		if(loginPage == null)
		{
			loginPage = new LoginPage(driver);
	
		}
		return loginPage;
	}
    
    public RegisterPage getregisterpage() {
        if(registerPage == null){
            registerPage = new RegisterPage(driver);
        }

        return registerPage;
    }
    
    public LinkedListPage getLinkedListPage() {
		if (linkedlistPage == null) {
			linkedlistPage = new LinkedListPage(driver);
		}

		return linkedlistPage;
	}
    
    public StackPage getStackPage() {
		if (stackPage == null) {
			stackPage = new StackPage(driver);
		}

		return stackPage;
	}
    
    public BaseTopicPage getCurrentTopicPage(String currentUrl) {
        if (currentUrl.matches(".*/stack/.*")) {
           return getStackPage();
        } else if (currentUrl.matches(".*/linked-list/.*")) {
            return getLinkedListPage();
        } //else if (currentUrl.matches(".*/$")) {
        	//return getLaunchPage();
        //}
        // Add more modules as needed
        throw new IllegalArgumentException("Unknown topic page for URL: " + currentUrl);
    } 

}
