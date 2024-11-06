package automation.example.com.support;

import org.openqa.selenium.WebDriver;

public class ManagerViews {

    public static void setView(WebDriver driver) {
        driver.manage().window().maximize();


    }
}
