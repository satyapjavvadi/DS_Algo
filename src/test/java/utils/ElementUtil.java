package utils;

import DriverManager.DriverFactory;
//import org.openqa.selenium.WebDriver;

public class ElementUtil {



    public static String getURL() {
        return DriverFactory.getDriver().getCurrentUrl();
    }

    public static String getTitle() {
    	return DriverFactory.getDriver().getTitle();
    }
}
