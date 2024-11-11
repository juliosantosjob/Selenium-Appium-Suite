package automation.example.com.support.instances;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browsers {
    private static final ChromeOptions chromeOptions = new ChromeOptions();
    private static final FirefoxOptions firefoxOptions = new FirefoxOptions();
    private static final EdgeOptions edgeOptions = new EdgeOptions();

    public static WebDriver getInstanceOptions(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                chromeOptions.addArguments("--log-level=3");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920x1080");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                return new FirefoxDriver();

            case "edge":
                edgeOptions.addArguments("--window-size=1920x1080");
                return new EdgeDriver(edgeOptions);

            case "chrome_headless":
                chromeOptions.addArguments("--log-level=3");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920x1080");
                return new ChromeDriver(chromeOptions);

            case "firefox_headless":
                firefoxOptions.addArguments("-headless");
                return new FirefoxDriver(firefoxOptions);

            case "edge_headless":
                edgeOptions.addArguments("headless");
                edgeOptions.addArguments("--window-size=1920x1080");
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Browser not supported: " + browserType);
        }
    }
}