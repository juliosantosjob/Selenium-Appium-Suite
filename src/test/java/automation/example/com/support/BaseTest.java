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
    private static WebDriver driver;
    private static WebDriverWait wait;


    public static void setup() {
        if (driver == null) {
           setWebDriver("chrome");
        }
    }

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

    public static WebDriverWait waitSupport(long... timeout) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(
                timeout.length > 0 ? timeout[0] : TIMEOUT));
    }

    public static void visit(String url) {
        try {
            getDriver().get(url);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao visitar a URL: " + url, e);
        }
    }

    public static WebElement getElement(By by, long... timeout) {
        try {
            wait = waitSupport(timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            return getDriver().findElement(by);
        } catch (TimeoutException e) {
            throw new TimeoutException("Elemento não encontrado: " + by);
        }
    }

    public static String grabText(By by, long... timeout) {

        try {
            wait = waitSupport(timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return getDriver().findElement(by).getText();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void click(By by, long... timeout) {
        WebElement element = getElement(by);

        try {
            wait = waitSupport(5);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitElement(By by, long... timeout) {
        try {
            wait = waitSupport(timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void type(By by, String text) {
        try {
            wait = waitSupport(5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            getElement(by).clear();
            getElement(by).sendKeys(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pressKey(By by, Keys button) {
        try {
            wait = waitSupport(5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            getElement(by).clear();
            getElement(by).sendKeys(button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void visible(By by) {
        try {
            wait = waitSupport(5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            getElement(by).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickText(String text) {
        try {
            By byElement = By.xpath("//*[contains(text(),'" + text + "')]");
            wait = waitSupport(5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));

            getElement(byElement).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void visibleText(String text) {
        try {
            By byElement = By.xpath("//*[contains(text(),'" + text + "')]");
            wait = waitSupport(5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
            getElement(byElement).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}