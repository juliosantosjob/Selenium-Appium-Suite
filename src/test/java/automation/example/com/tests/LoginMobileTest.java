package automation.example.com.tests;

import automation.example.com.actions.LoginMobileActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@Tag("regression_mobile")
@DisplayName("Login Mobile Tests")
public class LoginMobileTest extends Hooks {

    @Test
<<<<<<< HEAD
    @Tag("login")
    @DisplayName("Validando login no mobile com sucesso")
    public void validateLoginMobileSuccessFull() {
=======
    @Tag("login_mobile")
    @DisplayName("Cenário 01: Deve realizar um login no app")
    public void mustLogInToTheApp() {
>>>>>>> 14cebf99391fc90f87969879c989aa7228d96290
        changeDriverTo("mobile");

        LoginMobileActions loginMobile = new LoginMobileActions();
        loginMobile.openApp();
        loginMobile.fillForm("standard_user", "secret_sauce");
        loginMobile.submitLogin();
        assertEquals("PRODUCTS", loginMobile.getTextProduct());
    }
}