package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends  FramePage{

    @FindBy(xpath = "//div[@class='clearfix']//span[@class='current-price-value']")
    WebElement price;

    @FindBy(xpath = "//h1[contains(@class,'page-title')]")
    WebElement name;

    @FindBy(xpath = "//div[@class='price-buttons-item with-cart-btn']//button[@id='prod-card-buy-btn']")
    WebElement buyButton;

    public void buy(){
        waitClicability(buyButton);
        click(buyButton);
        VirtualCart.addPurchase(name.getText(),Double.parseDouble(price.getAttribute("data-price-value")));
    }

}
