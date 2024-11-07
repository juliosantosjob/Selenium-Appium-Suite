package automation.example.com.support;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class ManagerViews {

    public static void setView(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public void setViewportMobile(WebDriver driver) {
        driver.manage().window().setSize(new Dimension(375, 812)); // iPhone X
    }

    public void setViewportTablet(WebDriver driver) {
        driver.manage().window().setSize(new Dimension(768, 1024)); // iPad
    }

    public void setViewportDesktop(WebDriver driver) {
        driver.manage().window().setSize(new Dimension(1440, 900)); // Desktop comum
    }
}
