package automation.example.com.support;

import automation.example.com.support.instances.Browsers;
import automation.example.com.support.instances.Devices;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;

import static automation.example.com.support.ManagerViews.setNewViewport;
import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.fail;

public class BaseTest {
    private static final long TIMEOUT = parseLong(EnvProperties.getEnv("app.base.timeout"));
    private static final String browser = EnvProperties.getEnv("app.base.browser");
    private static final String platform = EnvProperties.getEnv("app.base.platform");
    private static WebDriverWait wait;
    protected static int testCount = 0;
    private static WebDriver driver;

    public static void setWebDriver(String browserType) {
        driver = Browsers.getInstanceOptions(browserType);
        setNewViewport(driver);
    }

    public static void setMobileDriver(String platformType) {
        if ("android".equalsIgnoreCase(platformType) || "ios".equalsIgnoreCase(platformType)) {
            System.err.println("***** As opçõoes de plataforma para o driver mobile devem ser: \"android\" ou \"ios\" *****");
        }

        try {
            if ("android".equalsIgnoreCase(platformType)) {
                driver = Devices.getInstanceAndroid();
            } else if ("ios".equalsIgnoreCase(platformType)) {
                driver = Devices.getInstanceIOS();
            } else {
                throw new IllegalArgumentException("Plataforma não suportada: " + platformType);
            }
        } catch (Exception e) {
            System.err.println("***** Erro ao instanciar o driver mobile *****" + e.getMessage());
        }
    }

    public static void changeDriverTo(String driverType) {
        if (!"mobile".equalsIgnoreCase(driverType) && !"web".equalsIgnoreCase(driverType)) {
            System.err.println("***** Opção para troca de driver nao encontrada, por favor escolha as opções: \"mobile\" ou \"web\" *****");
        }

        if ("mobile".equalsIgnoreCase(driverType)) {
            setMobileDriver(platform);
        } else if ("web".equalsIgnoreCase(driverType)) {
            setWebDriver(browser);
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
            throw new RuntimeException("Erro ao visitar a URL: " + url + e.getMessage());
        }
    }

    public static WebElement getElement(By by, long... timeout) {
        WebElement element = getDriver().findElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return element;

        } catch (TimeoutException e) {
            return fail("Timeout: The element is not visible within the defined time. \n" + e.getMessage());
        } catch (NoSuchElementException e) {
            return fail("Error: Element is not visible within the specified time. \n" + e.getMessage());
        } catch (ElementNotInteractableException e) {
            return fail("Error: Element is not interactable. \n" + e.getMessage());
        } catch (WebDriverException e) {
            return fail("Error: Webdriver failed. \n" + e.getMessage());
        }
    }

    public static String grabText(By by, long... timeout) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return element.getText();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void click(By by, long... timeout) {
        WebElement element = getElement(by);

        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitElement(By by, long... timeout) {
        try {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout.length > 0 ? timeout[0] : TIMEOUT));
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