package automation.example.com.pages;

import org.openqa.selenium.By;

public class LoginMobilePages {
    public static By appLogo = By.xpath("//*[@content-desc='test-Login']//android.widget.ImageView[1]");
    public static By usernameField = By.xpath("//*[@content-desc='test-Username']");
    public static By passwordField = By.xpath("//*[@content-desc='test-Password']");
    public static By loginButton = By.xpath("//*[@content-desc='test-LOGIN']");
    public static By textProduct = By.xpath("//*[@text='PRODUCTS']");
}