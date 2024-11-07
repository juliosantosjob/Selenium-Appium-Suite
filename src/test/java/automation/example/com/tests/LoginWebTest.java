package automation.example.com.tests;

import automation.example.com.actions.LoginWebActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@Tag("regression_web")
@DisplayName("Funcionalidade: Login Web")
public class LoginWebTest extends Hooks {

    @Test
<<<<<<< HEAD
    @Tag("loginWeb")
    @DisplayName("Validando login na web com sucesso")
    public void validateLoginWebSuccessFull() {
=======
    @Tag("login_web")
    @DisplayName("Cenário 01: Deve realizar um login no Web")
    public void mustLogInToTheWebApp() {
>>>>>>> 14cebf99391fc90f87969879c989aa7228d96290
        changeDriverTo("web");

        LoginWebActions loginWeb = new LoginWebActions();
        loginWeb.openApp();
        loginWeb.fillForm("standard_user", "secret_sauce");
        loginWeb.submitLogin();
        assertEquals("Products", loginWeb.getTextProduct());
    }
}