package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class Cart extends FramePage {

    @FindBy(xpath = "//div[@class='cart-items__products']")
    List<WebElement> listPurchases;


    public void warranty24(String name) {
        WebElement targetPurchase = driver.findElement(By.xpath("//div[@class='cart-items__products'" +
                " and contains(@data-cart-product-id,'') and contains(.,'"+name+"')]"));
        click(targetPurchase.findElement(By.xpath("//div[@data-commerce-target='basket_additional_warranty_24']")));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deletePurchase(String name) {

        waitClicability(driver.findElement(By.xpath("//div[@class='cart-items__product-info' and contains(.,'"+name+"')]//button[contains(text(), 'Удалить')]")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VirtualCart.removePurchase(driver.findElement(By.xpath("//div[@class='cart-items__product-info' and contains(.,'"+name+"')]//a[contains(text(),'"+name+"')]")).getText());
        click(driver.findElement(By.xpath("//div[@class='cart-items__product-info' and contains(.,'"+name+"')]//button[contains(text(), 'Удалить')]")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(targetPurchase.findElement(By.xpath("//a[contains(text(),'"+name+"')]")).getText());
//        System.out.println(VirtualCart.getPurchase().get(targetPurchase.findElement(By.xpath("//div[@class='cart-items__product-info' and contains(.,'\"+name+\"')]")).getText()));


    }

    public void addPurchase(String name) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement targetPurchase = driver.findElement(By.xpath("//div[@class='cart-items__product-info' and contains(.,'" + name + "')]"));
        waitClicability(targetPurchase.findElement(By.xpath("//button[contains(@data-commerce-action,'CART_ADD')]")));
        click(targetPurchase.findElement(By.xpath("//button[contains(@data-commerce-action,'CART_ADD')]")));
        //  Double oldSumm = VirtualCart.getPurchase().get(targetPurchase.findElement(By.xpath("//a[contains(text(),'"+name+"')]")).getText());
        System.out.println(Double.parseDouble(targetPurchase.findElement(By.xpath("//div[@class='total-amount']//span[@class='price__current']")).getText().replaceAll("\\s+", "")));
        VirtualCart.addPurchase(targetPurchase.findElement(By.xpath("//a[contains(text(),'" + name + "')]")).getText(),
                1.5 * Double.parseDouble(targetPurchase.findElement(By.xpath("//div[@class='total-amount']//span[@class='price__current']")).getText().replaceAll("\\s+", "")));
    }

    public void controlPurchase() {

        // Сравнение каждой покупки с сохраненным значением
        for (WebElement element : listPurchases) {
            String namePurchase = element.findElement(By.xpath("//a[@class='cart-items__product-name-link']")).getText();
            double pricePurchase = Double.parseDouble(element.findElement(By.xpath(
                    "//span[@class='price__current']")).getText().replaceAll("\\s+", ""));
            //Проверка    System.out.println(namePurchase + " " + pricePurchase);
            assertTrue(VirtualCart.getPurchase().get(namePurchase) == pricePurchase, "Цена покупок не соответствует");
        }
    }

    public void controlTotalPurchase() {
        //Проверка Общей Суммы
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double totalPricePurchase = Double.parseDouble(driver.findElement(By.xpath(
                "//div[@class='total-amount']//span[@class='price__current']")).getText().replaceAll("\\s+", ""));
        double summ = 0;
        for (Double price : VirtualCart.getPurchase().values()) {
            summ = summ + price;
        }
        ////a[contains(text(), 'Вернуть удалённый товар')]
        System.out.println(summ + "=" + totalPricePurchase);
        assertTrue(summ == totalPricePurchase, "Общая сумма не соответствует сохраненной");
    }

    //restore-last-removed

    public void returnLastRemoved() {
        WebElement targetPurchase = driver.findElement(By.xpath("//span[@class = 'restore-last-removed']"));
        waitClicability(targetPurchase);
        click(targetPurchase);
    }

    public void returnDeleted() {
        WebElement targetPurchase = driver.findElement(By.xpath("//a[contains(text(), 'Вернуть удалённый товар')]"));
        waitClicability(targetPurchase);
        click(targetPurchase);
    }
}
