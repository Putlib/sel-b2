import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

import java.util.List;


public class task13
{
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }


    private void waitForCartUpdate(String quantity){
        WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);
        wait.until(d->d.findElement(By.cssSelector("span.quantity")).getText().equals(quantity));
    }


    @Test
    public void Task13() {

        driver.navigate().to("http://localhost/litecart/en/");

        for (int j = 1; j < 4; j++){
            driver.findElement(By.cssSelector("div#box-most-popular .product")).click();
            String initialQuantity = driver.findElement(By.cssSelector("#cart .quantity")).getText();
            try {
                Select size = new Select(driver.findElement(By.name("options[Size]")));
                size.selectByValue("Small");
            } catch (Exception e){}
            driver.findElement(By.name("add_cart_product")).click();
            waitForCartUpdate(String.valueOf(j));
            String finalQuantity = driver.findElement(By.cssSelector("span.quantity")).getText();
            Assert.assertNotEquals("Количество товаров в корзине не изменилось",  initialQuantity, finalQuantity );
            driver.navigate().back();
        }

        driver.findElement(By.cssSelector("div #cart")).click();

        List<WebElement> productsInCant = driver.findElements(By.cssSelector("tr td.item"));
        int numProductsInCant = productsInCant.size();
        for (int i = 0; i<numProductsInCant; i++){
            WebElement prodTable = driver.findElement(By.cssSelector(".dataTable"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(prodTable));
        }
    }

    @After
    public void stop () {
        driver.quit();
        driver = null;
    }
}
