package automation.example.com.pages.mobile;

import automation.example.com.support.EnvProperties;
import org.openqa.selenium.By;

public class LoginMobilePages {
    public static By appLogo = By.xpath("//android.widget.ScrollView[@content-desc=\"test-Login\"]");
    public static By usernameField = By.xpath("//*[@content-desc='test-Username']");
    public static By passwordField = By.xpath("//*[@content-desc='test-Password']");
    public static By loginButton = By.xpath("//*[@content-desc='test-LOGIN']");
    public static By textProduct = By.xpath("//*[@text='PRODUCTS']");


//    protected static final String platform = EnvProperties.getEnv("app.base.platform");
//
//    public static By appLogo = platform.equalsIgnoreCase("android") ? By.xpath("//*[@content-desc='test-Login']//android.widget.ImageView[1]") : By.xpath("//XCUIElementTypeImage[@label='Swag Labs']");
//    public static By usernameField = platform.equalsIgnoreCase("android") ? By.xpath("//*[@content-desc='test-Username']") : By.xpath("//XCUIElementTypeTextField[@name='test-Username']");
//    public static By passwordField = platform.equalsIgnoreCase("android") ? By.xpath("//*[@content-desc='test-Password']") : By.xpath("//XCUIElementTypeSecureTextField[@name='test-Password']");
//    public static By loginButton = platform.equalsIgnoreCase("android") ? By.xpath("//*[@content-desc='test-LOGIN']") : By.xpath("//XCUIElementTypeButton[@name='test-LOGIN']");
//    public static By textProduct = platform.equalsIgnoreCase("android") ? By.xpath("//*[@text='PRODUCTS']") : By.xpath("//XCUIElementTypeStaticText[@name='PRODUCTS']");
}