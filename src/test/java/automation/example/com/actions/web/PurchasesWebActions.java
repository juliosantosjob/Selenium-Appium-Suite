package automation.example.com.actions.web;

import static automation.example.com.elements.web.PurchasesWebElements.*;
import static automation.example.com.support.BaseTest.*;
import static java.lang.System.out;

public class PurchasesWebActions {

    public void selectItem(String itemText) {
        getElementNearby(btnAddToCart, getByLocatorAndText(fldItemName, itemText), "below").click();
        out.println("- Adiciona o item " + itemText + " ao carrinho.");
    }

    public void goToCart() {
        click(btnShoppingCart);
        out.println("- Acessa o carrinho.");
    }
}
