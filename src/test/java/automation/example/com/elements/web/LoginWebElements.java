package automation.example.com.elements.web;

import org.openqa.selenium.By;

public class LoginWebElements {
    public static By appLogo = By.className("login_logo");
    public static By usernameInput = By.id("user-name");
    public static By passwordInput = By.id("password");
    public static By loginButton = By.id("login-button");
    public static By fieldProduct = By.className("product_label");
    public static By messageError = By.cssSelector("[data-test='error']");
}