package automation.example.com.elements.web;

import org.openqa.selenium.By;

public class PurchasesWebElements {
    public static By fldItemName = By.xpath("//*[@class='inventory_item_name']");
    public static By btnAddToCart = By.xpath("//*[@class='btn_primary btn_inventory']");
    public static By btnShoppingCart = By.xpath("//*[@class='shopping_cart_container']");
    public static By btnCheckout = By.xpath("//*[@class='btn_action checkout_button']");
    public static By fldFirstName = By.xpath("//*[@id='first-name']");
    public static By fldLastName = By.xpath("//*[@id='last-name']");
    public static By fldPostalCode = By.xpath("//*[@id='postal-code']");
    public static By btnContinue = By.xpath("//*[@class='btn_primary cart_button']");
    public static By btnFinish = By.xpath("//*[@class='btn_action cart_button']");
}