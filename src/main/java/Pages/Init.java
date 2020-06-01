package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Init {

    static WebDriverWait wait;
    private static WebDriver driver;
    public static Properties properties = TestProperties.getInstance().getProperties();


    public static void initWebDriver(){
        switch (properties.getProperty("browser")) {
            case "opera":
                System.setProperty("webdriver.opera.driver", properties.getProperty("driver"));
                driver = new OperaDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("driver"));
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("driver"));
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
        driver.get(properties.getProperty("app.url"));
    }

    public static void driverQuit(){
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
