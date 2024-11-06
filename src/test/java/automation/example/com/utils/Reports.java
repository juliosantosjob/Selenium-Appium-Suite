package automation.example.com.utils;

import automation.example.com.support.Hooks;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Reports extends Hooks {

    public static void attachScreenshot() {
        try {
            File screenshotAs = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
        } catch (Exception e) {
            System.out.println("Error implementing screenshot!");
        }
    }
}