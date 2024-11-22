package automation.example.com.usecases.web;

import automation.example.com.actions.web.LoginWebActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;

@Tag("regression")
@Tag("regression_web")
@Tag("regression_login_web")
@Tag("regression_login")
@DisplayName("Login na Web")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginWebTest extends Hooks {
    LoginWebActions loginWeb = new LoginWebActions();

    @BeforeEach
    public void setup() throws MalformedURLException {
        changeDriverTo("web");
    }

    @Test
    @Order(1)
    @Tag("login_web_successfully")
    @DisplayName("1 - Validando login na web com sucesso")
    public void validateLoginWebSuccessFull() {
        loginWeb.openSite();
        loginWeb.fillForm("standard_user", "secret_sauce");
        loginWeb.submitLogin();
        assertText("Products", loginWeb.getTextProduct());
    }

    @Test
    @Order(2)
    @Tag("loginWeb_invalid_user")
    @DisplayName("2 - Validando login web com usuário invalido")
    public void validateLoginWebInvalidUser() {
        loginWeb.openSite();
        loginWeb.fillForm("invalid_user", "secret_sauce");
        loginWeb.submitLogin();
        assertText("Epic sadface: Username and password do not match any user in this service", loginWeb.getErrorMessage());
    }

    @Test
    @Order(3)
    @Tag("loginWeb_invalid_password")
    @DisplayName("3 - Validando login web com senha invalida")
    public void validateLoginWebInvalidPassword() {
        loginWeb.openSite();
        loginWeb.fillForm("standard_user", "invalid_password");
        loginWeb.submitLogin();
        assertText("Epic sadface: Username and password do not match any user in this service", loginWeb.getErrorMessage());
    }

    @Test
    @Order(4)
    @Tag("loginWeb_invalid_user_password")
    @DisplayName("4 - Validando login web com usuário e senha invalidos")
    public void validateLoginWebInvalidUserPassword() {
        loginWeb.openSite();
        loginWeb.fillForm("invalid_user", "invalid_password");
        loginWeb.submitLogin();
        assertText("Epic sadface: Username and password do not match any user in this service", loginWeb.getErrorMessage());
    }
}