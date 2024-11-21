package automation.example.com.actions.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import static automation.example.com.support.BaseTest.*;

public class PurchasesWebActions {
    By fldItemName = By.xpath("//*[@class='inventory_item_name']");
    By btnAddToCart = By.xpath("//*[@class='btn_primary btn_inventory']");

    public void selectItem(String itemText) {
        WebElement itemNameElement = contains(fldItemName, itemText);
        WebElement addButton = getElement(RelativeLocator.with(btnAddToCart).below(itemNameElement));
        addButton.click();

        stop(5);
    }
}
