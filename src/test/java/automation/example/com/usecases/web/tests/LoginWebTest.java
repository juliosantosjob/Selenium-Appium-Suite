package automation.example.com.usecases.web.tests;


import automation.example.com.usecases.web.actions.LoginWebActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@Tag("regression_web")
@DisplayName("Login na Web")
public class LoginWebTest extends Hooks {

    @BeforeEach
    public void setup() {
        changeDriverTo("web");
    }

    @Test
    @Tag("loginWeb")
    @DisplayName("Validando login na web com sucesso")
    public void validateLoginWebSuccessFull() {
        LoginWebActions loginWeb = new LoginWebActions();
        loginWeb.openApp();
        loginWeb.fillForm("standard_user", "secret_sauce");
        loginWeb.submitLogin();
        assertEquals("Products", loginWeb.getTextProduct());
    }

    @Test
    @Tag("loginWeb_invalid_user")
    @DisplayName("Validando login web com usuário invalido")
    public void validateLoginWebInvalidUser() {
        LoginWebActions loginWeb = new LoginWebActions();
        loginWeb.openApp();
        loginWeb.fillForm("invalid_user", "secret_sauce");
        loginWeb.submitLogin();
        assertEquals("Epic sadface: Username and password do not match any user in this service", loginWeb.getErrorMessage());
    }
}