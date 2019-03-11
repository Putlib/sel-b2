import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class task12
{
    private WebDriver driver;

    @Before
    public void start () {
        driver = new ChromeDriver();
    }

    @Test
    public void Task12() throws InterruptedException {

        String productName = String.valueOf(Instant.now().getEpochSecond());

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();


        driver.findElement(By.cssSelector("li a[href$='/?app=catalog&doc=catalog']")).click();
        driver.findElement(By.cssSelector("[href*='edit_product']")).click();

        // Заполняем первую вкладку
        driver.findElement(By.xpath("//input[@name='status' and @value='1']")).click();
        driver.findElement(By.name("name[en]")).sendKeys(productName);
        driver.findElement(By.name("code")).sendKeys("123");
        driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
        Select root = new Select(driver.findElement(By.name("default_category_id")));
        root.selectByValue("1");
        driver.findElement(By.xpath("//input[@name='product_groups[]' and @value='1-3']")).click();
        driver.findElement(By.name("code")).sendKeys("99");
        driver.findElement(By.name("new_images[]")).sendKeys(System.getProperty("user.dir")+"/src/test/java/s1200.jpeg");
        driver.findElement(By.name("date_valid_from")).sendKeys("01032019");
        driver.findElement(By.name("date_valid_to")).sendKeys("31122020");

        // Заполняем вторую вкладку
        driver.findElement(By.cssSelector("[href='#tab-information']")).click();
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByValue("1");
        driver.findElement(By.name("keywords")).sendKeys("mandarin");
        driver.findElement(By.name("short_description[en]")).sendKeys("Test mandarin ducky");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Test mandarin ducky");
        driver.findElement(By.name("head_title[en]")).sendKeys("Mandarine ducky");
        driver.findElement(By.name("meta_description[en]")).sendKeys("metta description");

        // Заполняем третью вкладку
        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();
        driver.findElement(By.name("purchase_price")).sendKeys("34");
        Select currency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        currency.selectByValue("USD");

        driver.findElement(By.name("save")).click();

        // Проверяем, что добавленный товар присутствует в списке товаров
        List<WebElement> productsList = driver.findElements(By.xpath("//a[contains(@href, 'product_id')]"));
        int countriesCount = productsList.size();
        ArrayList<String> actualProductList = new ArrayList<String>();
        for (int j = 0; j < countriesCount; j+=2) {
            int productNum = j + 1;
            String productTitle = driver.findElement(By.xpath("(//a[contains(@href, 'product_id')])["+productNum+"]")).getText();
            actualProductList.add(productTitle);
        }
        Assert.assertTrue(String.format("Список товаров не содержит добавленный товар"), actualProductList.contains(productName));
    }

    @After
    public void stop () {
        driver.quit();
        driver = null;
    }
}
