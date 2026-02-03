package utils;

import DriverManager.DriverFactory;

public class NavigationUtil {

	public static void goTo(String module) {
	    String base = ConfigReader.getProperty("baseURL");

	    // Remove trailing slash if present
	    if (base.endsWith("/")) {
	        base = base.substring(0, base.length() - 1);
	    }

	    String url = base + "/" + module.toLowerCase() + "/";
	    DriverFactory.getDriver().navigate().to(url);
	}

}

