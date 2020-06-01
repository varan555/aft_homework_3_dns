package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FramePage {

    WebDriver driver;
    WebDriverWait wait;

    public FramePage() {
            driver = Init.getDriver();
            wait = new WebDriverWait(driver, 3);
            PageFactory.initElements(driver, this);
        }

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    WebElement searchInput;

    @FindBy(xpath = "//div[@class ='container']" +
            "//span[@class = 'ui-input-search__icon ui-input-search__icon_search ui-input-search__icon_presearch' ]")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='presearch__suggests']")
    WebElement listConcretely;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    WebElement cart;

    public void goToCart() {
        waitClicability(cart);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(cart);
    }

    public void searchByShort(String text) {
        waitClicability(searchInput);
        enter(searchInput, text);
        waitClicability(searchButton);
        click(searchButton);
    }

    public void selectConcretely(String text) {
        waitClicability(listConcretely);
        WebElement concretely = driver.findElement(By.xpath("//a[contains(text(), '"+text+"')]"));
        waitClicability(concretely);
        click(concretely);

    }
    public void isElementDisplayed(WebElement elementBy) {
        waitClicability(elementBy);
        assertTrue(elementBy.isDisplayed());
    }

    public void waitClicability(WebElement elementBy) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementBy);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        wait.until(ExpectedConditions.visibilityOf(elementBy));
        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
         }

    public void enter(WebElement elementBy, String text) {
        waitClicability(elementBy);
        elementBy.sendKeys(text);
    }

    public void checkFields(By field, String value) {
        assertTrue(driver.findElement(field).getAttribute("value").equals(value), value);
    }


    public void click(WebElement elementBy) {
        waitClicability(elementBy);
        elementBy.click();
    }



}
