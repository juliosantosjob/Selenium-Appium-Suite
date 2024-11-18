package automation.example.com.usecases.mobile;

import automation.example.com.actions.mobile.LoginMobileActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@Tag("regression_mobile")
@DisplayName("Login no Mobile")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginMobileTest extends Hooks {

    @BeforeEach
    public void setup() throws MalformedURLException {
        changeDriverTo("mobile");
    }

    @Test
    @Order(1)
    @Tag("login")
    @DisplayName("1 - Validando login no mobile com sucesso")
    public void validateLoginMobileSuccessFull() throws MalformedURLException {
        LoginMobileActions loginMobile = new LoginMobileActions();
        loginMobile.openApp();
        loginMobile.fillForm("standard_user", "secret_sauce");
        loginMobile.submitLogin();
        assertEquals("PRODUCTS", loginMobile.getTextProduct());
    }
}