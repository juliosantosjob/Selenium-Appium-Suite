package automation.example.com.usecases.web;

import automation.example.com.actions.web.LoginWebActions;
import automation.example.com.actions.web.PurchasesWebActions;
import automation.example.com.support.Hooks;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;

import java.io.IOException;
import java.net.MalformedURLException;

import static automation.example.com.utils.Dynamics.readJson;

@Tag("regression")
@Tag("regression_web")
@Tag("purchases_web")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Compra de produtos na web")
public class PurchasesWebTest extends Hooks {
    private final LoginWebActions loginWeb = new LoginWebActions();
    private final PurchasesWebActions productsWeb = new PurchasesWebActions();
    private final String product = readJson("src/test/resources/samples/products.json", "productList");

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
        productsWeb.selectItem(product);
        productsWeb.goToCart();
        productsWeb.checkItemOnCart(product);
        productsWeb.goToCheckout();
        productsWeb.fillPersonalData("John", "Doe", "12345");
        productsWeb.submitCheckout();
        productsWeb.finishPurchase();
    }
}
