package automation.example.com.elements.mobile;

import automation.example.com.support.EnvProperties;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginMobileElements {
    protected static final String platform = EnvProperties.getEnv("app.base.platform");

    public static By appLogo = platform.equalsIgnoreCase("android") ? By.xpath("//*[@content-desc='test-Login']//android.widget.ImageView[1]") : By.xpath("//XCUIElementTypeImage[@label='Swag Labs']");
    public static By usernameField = platform.equalsIgnoreCase("android") ? By.xpath("//*[@content-desc='test-Username']") : By.xpath("//XCUIElementTypeTextField[@name='test-Username']");
    public static By passwordField = platform.equalsIgnoreCase("android") ? By.xpath("//*[@content-desc='test-Password']") : By.xpath("//XCUIElementTypeSecureTextField[@name='test-Password']");
    public static By loginButton = platform.equalsIgnoreCase("android") ? By.xpath("//*[@content-desc='test-LOGIN']") : By.xpath("//XCUIElementTypeButton[@name='test-LOGIN']");
    public static By textProduct = platform.equalsIgnoreCase("android") ? By.xpath("//*[@text='PRODUCTS']") : By.xpath("//XCUIElementTypeStaticText[@name='PRODUCTS']");
}
