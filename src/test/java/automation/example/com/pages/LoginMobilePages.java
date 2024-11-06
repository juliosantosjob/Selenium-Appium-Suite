package automation.example.com.pages;

import org.openqa.selenium.By;

public class LoginMobilePages {
    public static By appLogo = By.xpath("//android.widget.ScrollView[@content-desc='test-Login']//android.widget.ImageView[1]");
    public static By usernameField = By.xpath("//android.widget.EditText[@content-desc='test-Username']");
    public static By passwordField = By.xpath("//android.widget.EditText[@content-desc='test-Password']");
    public static By loginButton = By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']");
    public static By textProduct = By.xpath("//android.widget.TextView[@text='PRODUCTS']");
}