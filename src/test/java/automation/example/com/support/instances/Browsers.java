package automation.example.com.support.instances;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browsers {

    public static WebDriver getInstanceOptions(String browserType) {
        EdgeOptions edgeOptions = new EdgeOptions();
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        switch (browserType.toLowerCase()) {
            case "chrome":
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920x1080");
                return new ChromeDriver(chromeOptions);

            case "edge":
                edgeOptions.addArguments("--window-size=1920x1080");
                return new EdgeDriver(edgeOptions);

            case "firefox":
                return new FirefoxDriver();

            case "chrome_headless":
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920x1080");
                return new ChromeDriver(chromeOptions);

            case "edge_headless":
                edgeOptions.addArguments("headless");
                edgeOptions.addArguments("--window-size=1920x1080");
                return new EdgeDriver(edgeOptions);

            case "firefox_headless":
                firefoxOptions.addArguments("-headless");
                return new FirefoxDriver(firefoxOptions);

            default:
                throw new IllegalArgumentException("Browser not supported: " + browserType);
        }
    }
}