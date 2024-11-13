package automation.example.com.usecases.mobile.tests;

import automation.example.com.usecases.mobile.actions.LoginMobileActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@Tag("regression_mobile")
@DisplayName("Login no Mobile")
public class LoginMobileTest extends Hooks {

    @Test
    @Tag("login")
    @DisplayName("Validando login no mobile com sucesso")
    public void validateLoginMobileSuccessFull() {
        changeDriverTo("mobile");

        LoginMobileActions loginMobile = new LoginMobileActions();
        loginMobile.openApp();
        loginMobile.fillForm("standard_user", "secret_sauce");
        loginMobile.submitLogin();
        assertEquals("PRODUCTS", loginMobile.getTextProduct());
    }
}