package automation.example.com.actions.web;

import static automation.example.com.elements.web.PurchasesWebElements.*;
import static automation.example.com.support.BaseTest.*;
import static java.lang.System.out;

public class PurchasesWebActions {

    public void selectItem(String itemText) {
        locateElementNear(btnAddToCart, "below", getByLocatorAndText(fldItemName, itemText)).click();
        out.println("- Adiciona o item \"" + itemText + "\" ao carrinho");
    }

    public void goToCart() {
        click(btnShoppingCart);
        out.println("- Acessa o carrinho");
    }

    public void checkItemOnCart(String itemText) {
        getByLocatorAndText(fldItemName, itemText).isDisplayed();
        out.println("- Verifica o item \"" + itemText + "\" no carrinho");
    }

    public void goToCheckout() {
        click(btnCheckout);
        visibleText("Checkout: Your Information");
        out.println("- Vai para o checkout");
    }

    public void fillPersonalData(String firstName, String lastName, String postalCode) {
        type(fldFirstName, firstName);
        type(fldLastName, lastName);
        type(fldPostalCode, postalCode);
        out.println("- Preenche os dados pessoais");
    }

    public void submitCheckout() {
        click(btnContinue);
        out.println("- Submete o checkout");
    }

    public void finishPurchase() {
        click(btnFinish);
        out.println("- Finaliza a compra");
    }
}