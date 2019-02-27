import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public class task8
{
    private WebDriver driver;

    @Before
    public void start () {
        driver = new ChromeDriver();
    }

    @Test
    public void Task8() {
        driver.navigate().to("http://localhost/litecart/en/");

        List<WebElement> products = driver.findElements(By.xpath("(//*/li[contains(@class,'product')])"));
        int productCount = products.size();
        for (int i = 0; i < productCount; i++){
            int productnum = i+1;
            List<WebElement> stickers = driver.findElements(By.xpath("(//*/li[contains(@class,'product')])[" + productnum + "]//*[contains(@class, 'sticker')]"));
            int stickerCount = stickers.size();
            int expectedStickerCount = 1;
            Assert.assertEquals (String.format("Количество стикеров для продукта №" + productnum + " не равно 1"),expectedStickerCount,stickerCount);
        }
    }

    @After
    public void stop () {
        driver.quit();
        driver = null;
    }
}
