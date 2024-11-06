package automation.example.com.pages;

import automation.example.com.support.EnvProperties;
import org.openqa.selenium.By;

public class LoginWebPages {
    public static final String baseUrl = EnvProperties.getEnv("app.base.url");
    public static final String titleLoginPage = "Swag Labs";
    public static By usernameInput = By.id("user-name");
    public static By passwordInput = By.id("password");
    public static By loginButton = By.id("login-button");
    public static By fieldProduct = By.cssSelector("[data-test=\"title\"]");
}