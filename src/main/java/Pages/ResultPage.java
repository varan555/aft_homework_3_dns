package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ResultPage extends FramePage {


    @FindBy(xpath = "//div[@class='product-info__title-link']")
    List<WebElement> listResults;

    public void searchConcretly(String nameProduct){

//        WebElement waiting = driver.findElement(By.xpath("//button[@data-role='show-more-btn']"));
//        waitClicability(waiting);
        WebElement nameConcretly = driver.findElement(By.xpath("//div[@class='product-info__title-link' and contains(., '"+nameProduct+"')]"));
      //  waitClicability(nameConcretly);
//        waitClicability(nameConcretly);
        click(nameConcretly);
    }

}
