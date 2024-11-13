package automation.example.com.support;

import automation.example.com.support.instances.Browsers;
import automation.example.com.support.instances.Devices;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static automation.example.com.support.ManagerViews.setNewViewport;
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
            if (platformType.equals("android")) {
                driver = Devices.getInstanceAndroid();
            } else if (platformType.equals("ios")) {
                driver = Devices.getInstanceIOS();
            } else {
                throw new IllegalArgumentException("Plataforma não suportada: " + platformType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeDriverTo(String driverType) {
        if (driverType.equals("mobile")) {
            setMobileDriver(platform);
        } else if (driverType.equals("web")) {
            setWebDriver(browser);
            setNewViewport();
        } else {
            throw new IllegalArgumentException("Navegador não suportado: " + driverType);
        }
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
            throw new RuntimeException("Erro ao visitar a URL: " + url, e);
        }
    }

    public static WebElement getElement(By by, long... timeout) {
        WebElement element = getDriver().findElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(
                    timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            return element;
        } catch (TimeoutException e) {
            throw new TimeoutException("Elemento não encontrado: " + by);
        }
    }

    public static String grabText(By by, long... timeout) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(
                    timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return element.getText();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void click(By by, long... timeout) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(
                    timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitElement(By by, long... timeout) {
        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(
                    timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            e.printStackTrace();
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