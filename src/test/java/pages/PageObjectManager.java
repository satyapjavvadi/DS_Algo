package pages;

import org.openqa.selenium.WebDriver;

import DriverManager.DriverFactory;

public class PageObjectManager {

	private final WebDriver driver;
	private LaunchPage launchPage;
	private DataStructures dataStructures;
	private Graph graph;
	
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

	public DataStructures getDataStructures() {
		if (dataStructures == null) {
			dataStructures = new DataStructures(driver);
		}
		
			return dataStructures;
	}
	
	public Graph getGraph() {
	if (graph == null) {
		graph = new Graph(driver);
	}
		return graph;
}
}