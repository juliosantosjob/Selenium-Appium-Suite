package automation.example.com.usecases.mobile;

import automation.example.com.actions.mobile.LoginMobileActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;

import java.net.MalformedURLException;

@Tag("regression")
@Tag("regression_mobile")
@Tag("regression_login_mobile")
@Tag("regression_login")
@DisplayName("Login no Mobile")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginMobileTest extends Hooks {
    private final LoginMobileActions loginMobile = new LoginMobileActions();

    @BeforeEach
    public void setup() throws MalformedURLException {
        changeDriverTo("mobile");
    }

    @Test
    @Order(1)
    @Tag("login_mobile_successfully")
    @DisplayName("1 - Validando login no mobile com sucesso")
    public void validateLoginMobileSuccessFull() {
        loginMobile.openApp();
        loginMobile.fillForm("standard_user", "secret_sauce");
        loginMobile.submitLogin();
        assertText("PRODUCTS", loginMobile.getTextProduct());
    }

    @Test
    @Order(2)
    @Tag("login_mobile_invalid_user")
    @DisplayName("2 - Validando login mobile com usuário invalido")
    public void validateLoginMobileInvalidUser() {
        loginMobile.openApp();
        loginMobile.fillForm("invalid_user", "secret_sauce");
        loginMobile.submitLogin();
        assertTextViewDisplayed("Username and password do not match any user in this service.");
    }

    @Test
    @Order(3)
    @Tag("login_mobile_invalid_password")
    @DisplayName("3 - Validando login mobile com senha invalida")
    public void validateLoginMobileInvalidPassword() {
        loginMobile.openApp();
        loginMobile.fillForm("standard_user", "invalid_password");
        loginMobile.submitLogin();
        assertTextViewDisplayed("Username and password do not match any user in this service.");
    }

    @Test
    @Order(4)
    @Tag("login_mobile_invalid_user_password")
    @DisplayName("4 - Validando login mobile com usuário e senha invalidos")
    public void validateLoginMobileInvalidUserPassword() {
        loginMobile.openApp();
        loginMobile.fillForm("invalid_user", "invalid_password");
        loginMobile.submitLogin();
        assertTextViewDisplayed("Username and password do not match any user in this service.");
    }

    @Test
    @Order(5)
    @Tag("login_mobile_blank_user")
    @DisplayName("5 - Validando login com usuario em branco")
    public void validateLoginMobileWithBlankUser() {
        loginMobile.openApp();
        loginMobile.fillForm("", "secret_sauce");
        loginMobile.submitLogin();
        assertTextViewDisplayed("Username is required");
    }

    @Test
    @Order(6)
    @Tag("login_mobile_blank_password")
    @DisplayName("6 - Validando login com senha em branco")
    public void validateLoginMobileWithBlankPassword() {
        loginMobile.openApp();
        loginMobile.fillForm("standard_user", "");
        loginMobile.submitLogin();
        assertTextViewDisplayed("Password is required");
    }

    @Test
    @Order(7)
    @Tag("login_mobile_blank_user_password")
    @DisplayName("7 - Validando login com usuario e senha em branco")
    public void validateLoginMobileWithBlankUserAndPassword() {
        loginMobile.openApp();
        loginMobile.submitLogin();
        assertTextViewDisplayed("Username is required");
    }
}