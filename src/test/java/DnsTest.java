import Pages.*;
import org.junit.jupiter.api.Test;

public class DnsTest extends BaseTest {

    @Test
    public void firstTest() {

        FramePage test = new FramePage();
        test.searchByShort("playstation");
        ResultPage resultPage = new ResultPage();
        resultPage.searchConcretly("PlayStation 4 Slim Black");
        ProductPage productPage = new ProductPage();
        productPage.buy();
        productPage.searchByShort("Detroit");
        productPage.buy();
        Cart cart = new Cart();
        test.goToCart();
        cart.controlPurchase();
        cart.controlTotalPurchase();
        cart.warranty24("PlayStation");
        cart.deletePurchase("Detroit");
        cart.addPurchase("PlayStation");
        cart.addPurchase("PlayStation");
        cart.controlTotalPurchase();
        cart.deletePurchase("PlayStation");
        cart.returnDeleted();

    }
}
