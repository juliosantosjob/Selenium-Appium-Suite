package automation.example.com.usecases.web;

import automation.example.com.actions.web.LoginWebActions;
import automation.example.com.actions.web.PurchasesWebActions;
import automation.example.com.support.Hooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;

import java.net.MalformedURLException;

@Tag("regression")
@Tag("regression_web")
@Tag("purchases_web")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Compra de produtos na web")
public class PurchasesWebTest extends Hooks {
    LoginWebActions loginWeb = new LoginWebActions();
    PurchasesWebActions productsWeb = new PurchasesWebActions();

    @BeforeEach
    public void setup() throws MalformedURLException {
        changeDriverTo("web");
        loginWeb.doLogin("standard_user", "secret_sauce");
    }

    @Test
    @Order(1)
    @Tag("products_web")
    @DisplayName("1 - Validando compra de produto na web")
    public void purchaseProductOnSite() {
        String product = "Sauce Labs Bolt T-Shirt";

        productsWeb.selectItem(product);
        productsWeb.goToCart();
        productsWeb.checkItemOnCart(product);
        productsWeb.goToCheckout();
    }
}
