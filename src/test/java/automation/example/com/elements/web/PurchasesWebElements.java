package automation.example.com.elements.web;

import org.openqa.selenium.By;

public class PurchasesWebElements {
    public static By fldItemName = By.xpath("//*[@class='inventory_item_name']");
    public static By btnAddToCart = By.xpath("//*[@class='btn_primary btn_inventory']");
    public static By btnShoppingCart = By.xpath("//*[@class='shopping_cart_container']");
    public static By btnCheckout = By.xpath("//*[@class='btn_action checkout_button']");
}