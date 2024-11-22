package automation.example.com.actions.web;

import static automation.example.com.elements.web.PurchasesWebElements.*;
import static automation.example.com.support.BaseTest.*;
import static java.lang.System.out;

public class PurchasesWebActions {

    public void selectItem(String itemText) {
        locateElementNear(btnAddToCart, "below", getByLocatorAndText(fldItemName, itemText)).click();
        out.println("- Adiciona o item " + itemText + " ao carrinho.");
    }

    public void goToCart() {
        click(btnShoppingCart);
        out.println("- Acessa o carrinho.");
    }
}