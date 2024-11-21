package automation.example.com.support;

import automation.example.com.support.instances.Browsers;
import automation.example.com.support.instances.Devices;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static automation.example.com.support.HelpConfig.helpConfig;
import static java.lang.Long.parseLong;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class BaseTest {
    protected static final long TIMEOUT = parseLong(EnvProperties.getEnv("app.base.timeout"));
    protected static final String browser = EnvProperties.getEnv("app.base.browser");
    protected static final String platform = EnvProperties.getEnv("app.base.platform");
    protected static WebDriverWait wait;
    protected static WebDriver driver;
    protected static int testCount = 0;

    public static WebDriver getWebDriver(String browserType) {
        return Browsers.getInstanceOptions(browserType);
    }

    public static WebDriver getMobileDriver(String platformType) throws MalformedURLException {
        if ("android".equalsIgnoreCase(platformType)) {
            return Devices.getInstanceAndroid();
        } else if ("ios".equalsIgnoreCase(platformType)) {
            return Devices.getInstanceIOS();
        } else {
            throw new IllegalArgumentException("Platform not supported: " + platformType);
        }
    }

    public static void changeDriverTo(String driverType) throws MalformedURLException {
        if (driver != null) {
            tearDown();
        }

        if ("mobile".equalsIgnoreCase(driverType)) {
            driver = getMobileDriver(platform);
        } else if ("web".equalsIgnoreCase(driverType)) {
            driver = getWebDriver(browser);
        } else {
            throw new IllegalArgumentException("Driver type not supported: " + driverType);
        }
        helpConfig(driverType);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void visit(String url) {
        try {
            getDriver().get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebElement getElement(By by, long... timeout) {
        WebElement element = getDriver().findElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return element;

        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout: The element is not visible within the defined time. \n" + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: No such element with the locator. " + by + "\n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static WebElement getElementByText(String text, long... timeout) {
        By by = By.xpath("//*[text()='" + text + "']");
        WebElement element = getDriver().findElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return element;

        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout: The element is not visible within the defined time. \n" + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: No such element with the text '" + text + "' \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element with text '" + text + "' is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static WebElement contains(By by, String text) {
        WebElement element = getElement(by).findElement(By.xpath("//*[contains(text(),'" + text + "')]"));

        if (!element.isDisplayed()) {
            throw new NoSuchElementException("Error: No such element with the text '" + text + "' \n");
        }

        return element;
    }


    public static String grabText(By by, long... timeout) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return element.getText();

        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout: The element is not visible within the defined time. \n" + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static void click(By by, long... timeout) {
        WebElement element = getElement(by);
        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            element.click();

        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout: The element is not visible within the defined time. \n" + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static void waitElement(By by, long... timeout) {
        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout: The element is not visible within the defined time. \n" + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public void waitBeClickable(By by) {
        WebElement element = getDriver().findElement(by);
        int maxAttempts = 5;
        int attempt = 1;

        try {
            while (attempt <= maxAttempts) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(by));
                    element.click();
                    break;
                } catch (Exception e) {
                    attempt++;
                }
            }
        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout: The element is not visible within the defined time. \n" + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static void type(By by, String text) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            element.clear();

            for (char set : text.toCharArray()) {
                sleep(1);
                element.sendKeys(String.valueOf(set));
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("Error: Interrupted. \n" + e.getMessage());
        }
    }

    public static void pressKey(By by, Keys button) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));

            element.sendKeys(button);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static void visible(By by) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            element.isDisplayed();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static void clickByText(String text) {
        By byElement = By.xpath("//*[contains(text(),\"" + text + "\")]");

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
            getElement(byElement).click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static void visibleText(String text) {
        By byElement = By.xpath("//*[contains(text(),\"" + text + "\")]");

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
            getElement(byElement).isDisplayed();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static void assertTextViewDisplayed(String text) {
        By byElement = By.xpath("//android.widget.TextView[@text=\"" + text + "\"]");

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
            getElement(byElement).isDisplayed();
            out.println("- Exibe o TextView: " + text);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static void stop(long time) {
        try {
            sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}