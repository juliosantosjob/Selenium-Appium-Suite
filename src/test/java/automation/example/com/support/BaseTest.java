package automation.example.com.support;

import automation.example.com.support.instances.Browsers;
import automation.example.com.support.instances.Devices;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static automation.example.com.support.ManagerViews.setNewViewport;

public class BaseTest {
    private static final String TIMEOUT = EnvProperties.getEnv("app.base.timeout");
    private static final String browser = EnvProperties.getEnv("app.base.browser");
    private static final String platform = EnvProperties.getEnv("app.base.platform");
    private static WebDriver driver;
    private static WebDriverWait wait;

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
        }
        catch (Exception e) {
            throw new RuntimeException("Erro ao visitar a URL: " + url, e);
        }
    }

    public static String grabText(By by) {
        waitElement(by, 5);

        try {
            return getElement(by).getText();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static WebElement getElement(By by) {
        waitElement(by, 5);

        try {
            return getDriver().findElement(by);
        } catch (TimeoutException e) {
            throw new TimeoutException("Elemento não encontrado: " + by);
        }
    }

    public static void click(By by) {
        waitElement(by, 5);

        try {
            getElement(by).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitElement(By by, long... timeout) {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(
                timeout.length > 0 ? timeout[0] : Long.parseLong(TIMEOUT)));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void type(By by, String text) {
        waitElement(by, 5);

        try {
            getElement(by).sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pressKey(By by, Keys button) {
        try {
            getElement(by).sendKeys(button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void visible(By by) {
        waitElement(by, 5);

        try {
            getElement(by).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickText(String text) {
        try {
            getElement(By.xpath("//*[text()='" + text + "']")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void visibleText(String text) {
        try {
            getElement(By.xpath("//*[contains(text(),'" + text + "')]")).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}