package automation.example.com.utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

import static automation.example.com.support.BaseTest.getDriver;

public class Reports {

    public static void attachScreenshot() {
        try {
            File screenshotAs = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
        } catch (Exception e) {
            System.out.println("Error implementing screenshot!");
        }
    }
}