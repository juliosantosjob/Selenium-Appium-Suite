package automation.example.com.actions.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static automation.example.com.support.BaseTest.*;

public class PurchasesWebActions {
    By fldItemName = By.xpath("//*[@class='inventory_item']");
    By btnAddToCart = By.xpath("//*[@class='btn_primary btn_inventory']");

    public void selectItem(String itemText) {
        WebElement item = contains(fldItemName, itemText);
        WebElement nextElement = item.findElement(By.xpath(".//following::*[@class='btn_primary btn_inventory']"));

        nextElement.click();
        sleepToDebug(5);
    }

}