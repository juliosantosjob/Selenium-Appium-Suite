package automation.example.com.usecases.mobile;

import automation.example.com.actions.mobile.LoginMobileActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@Tag("regression_mobile")
@DisplayName("Login no Mobile")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginMobileTest extends Hooks {

    @Test
    @Order(1)
    @Tag("login")
    @DisplayName("1 - Validando login no mobile com sucesso")
    public void validateLoginMobileSuccessFull() throws MalformedURLException {
        changeDriverTo("mobile");

        LoginMobileActions loginMobile = new LoginMobileActions();
        loginMobile.openApp();
        loginMobile.fillForm("standard_user", "secret_sauce");
        loginMobile.submitLogin();
        assertEquals("PRODUCTS", loginMobile.getTextProduct());
    }
}