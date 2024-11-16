package automation.example.com.usecases.web.tests;

import automation.example.com.usecases.web.actions.LoginWebActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@Tag("regression_web")
@DisplayName("Login na Web")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginWebTest extends Hooks {

    @BeforeEach
    public void setup() {
        changeDriverTo("web");
    }

    @Test
    @Order(1)
    @Tag("login_web")
    @DisplayName("1 - Validando login na web com sucesso")
    public void validateLoginWebSuccessFull() {
        LoginWebActions loginWeb = new LoginWebActions();
        loginWeb.openApp();
        loginWeb.fillForm("standard_user", "secret_sauce");
        loginWeb.submitLogin();
        assertEquals("Products", loginWeb.getTextProduct());
    }

    @Test
    @Order(2)
    @Tag("loginWeb_invalid_user")
    @DisplayName("2 - Validando login web com usuário invalido")
    public void validateLoginWebInvalidUser() {
        LoginWebActions loginWeb = new LoginWebActions();
        loginWeb.openApp();
        loginWeb.fillForm("invalid_user", "secret_sauce");
        loginWeb.submitLogin();
        assertEquals("Epic sadface: Username and password do not match any user in this service", loginWeb.getErrorMessage());
    }

    @Test
    @Order(3)
    @Tag("loginWeb_invalid_password")
    @DisplayName("3 - Validando login web com senha invalida")
    public void validateLoginWebInvalidPassword() {
        LoginWebActions loginWeb = new LoginWebActions();
        loginWeb.openApp();
        loginWeb.fillForm("standard_user", "invalid_password");
        loginWeb.submitLogin();
        assertEquals("Epic sadface: Username and password do not match any user in this service", loginWeb.getErrorMessage());
    }
}