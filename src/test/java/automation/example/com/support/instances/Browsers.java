package automation.example.com.support.instances;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.logging.Level;

public class Browsers {

    public static WebDriver getInstanceOptions(String browserType) {
        EdgeOptions edgeOptions = new EdgeOptions();
        ChromeOptions chromeOptions = new ChromeOptions();

        switch (browserType.toLowerCase()) {
            case "chrome":
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920x1080");
                return new ChromeDriver(chromeOptions);

            case "edge":
                edgeOptions.addArguments("--window-size=1920x1080");
                return new EdgeDriver(edgeOptions);

            case "chrome_headless":
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920x1080");
                return new ChromeDriver(chromeOptions);

            case "edge_headless":
                edgeOptions.addArguments("headless");
                edgeOptions.addArguments("--window-size=1920x1080");
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Browser not supported: " + browserType);
        }
    }
}