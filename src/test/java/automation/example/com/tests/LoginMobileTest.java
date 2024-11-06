package automation.example.com.tests;


import automation.example.com.actions.LoginMobileActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@DisplayName("Login Mobile Tests")
public class LoginMobileTest extends Hooks {

    @Test
    @Tag("login")
    @DisplayName("Cenário 01: Deve realizar um login no app")
    public void mustLogInToTheApp() {
        changeDriverTo("mobile");

        LoginMobileActions loginMobile = new LoginMobileActions();
        loginMobile.openApp();
        loginMobile.fillForm("standard_user", "secret_sauce");
        loginMobile.submitLogin();
        assertEquals("PRODUCTS", loginMobile.getTextProduct());

        tearDown();
    }
}