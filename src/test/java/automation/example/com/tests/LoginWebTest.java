package automation.example.com.tests;

import automation.example.com.actions.LoginWebActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@DisplayName("Funcionalidade: Login Web")
public class LoginWebTest extends Hooks {

    @Test
    @Tag("loginWeb")
    @DisplayName("Cenário 01: Deve realizar um login no Web")
    public void mustLogInToTheWebApp() {
        changeDriverTo("web");

        LoginWebActions loginWeb = new LoginWebActions();
        loginWeb.openApp();
        loginWeb.fillForm("standard_user", "secret_sauce");
        loginWeb.submitLogin();
        assertEquals("Products", loginWeb.getTextProduct());
    }
}