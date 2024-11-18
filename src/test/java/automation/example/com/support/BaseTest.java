package automation.example.com.support;

import automation.example.com.support.instances.Browsers;
import automation.example.com.support.instances.Devices;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static automation.example.com.support.ManagerViews.*;
import static java.lang.Long.parseLong;

public class BaseTest {
    private static final long TIMEOUT = parseLong(EnvProperties.getEnv("app.base.timeout"));
    private static final String browser = EnvProperties.getEnv("app.base.browser");
    private static final String platform = EnvProperties.getEnv("app.base.platform");

    protected static int testCount = 0;
    private static WebDriverWait wait;
    private static WebDriver driver;

    public static void setWebDriver(String browserType) {
        driver = Browsers.getInstanceOptions(browserType);
    }

    public static void setMobileDriver(String platformType) {
        try {
            if ("android".equalsIgnoreCase(platformType)) {
                driver = Devices.getInstanceAndroid();
            } else if ("ios".equalsIgnoreCase(platformType)) {
                driver = Devices.getInstanceIOS();
            } else {
                throw new IllegalArgumentException("Platform not supported: " + platformType);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void changeDriverTo(String driverType) {
        if ("mobile".equalsIgnoreCase(driverType)) {
            setMobileDriver(platform);
        } else if ("web".equalsIgnoreCase(driverType)) {
            setWebDriver(browser);
        } else {
            throw new IllegalArgumentException("Invalid driver type: " + driverType);
        }
        helpConfig();
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
            throw new NoSuchElementException("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            throw new WebDriverException("Error: Webdriver failed. \n" + e.getMessage());
        }
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
        int maxAttempts = 10;
        int attempt = 1;
        boolean elementClickable = false;

        try {
            while (attempt <= maxAttempts) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(by));
                    element.click();
                    elementClickable = true;
                    break;
                } catch (Exception e) {
                    attempt++;
                }
            }

            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
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

            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pressKey(By by, Keys button) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));

            element.sendKeys(button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void visible(By by) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickText(String text) {
        By byElement = By.xpath("//*[contains(text(),\"" + text + "\")]");

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));

            getElement(byElement).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void visibleText(String text) {
        By byElement = By.xpath("//*[contains(text(),\"" + text + "\")]");

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));

            getElement(byElement).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}