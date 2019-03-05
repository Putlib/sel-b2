import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class task10 {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void Task10() {
        driver.navigate().to("http://localhost/litecart/en/");

        String mainPageName = driver.findElement(By.cssSelector("#box-campaigns .product .name")).getText();
        String mainPageRegularPrice = driver.findElement(By.cssSelector("#box-campaigns .product .regular-price")).getText();
        String mainPageCampaignPrice = driver.findElement(By.cssSelector("#box-campaigns .product .campaign-price")).getText();
        int mainRegOffsetHeight = Integer.parseInt (driver.findElement(By.cssSelector("#box-campaigns .product .regular-price")).getAttribute("offsetHeight"));
        int mainCampOffsetHeight = Integer.parseInt (driver.findElement(By.cssSelector("#box-campaigns .product .campaign-price")).getAttribute("offsetHeight"));
        String mainRegNodeName = driver.findElement(By.cssSelector("#box-campaigns .product .regular-price")).getAttribute("nodeName");
        String mainCampNodeName = driver.findElement(By.cssSelector("#box-campaigns .product .campaign-price")).getAttribute("nodeName");
        String mainRegColor = driver.findElement(By.cssSelector("#box-campaigns .product .regular-price")).getCssValue("color");
        String[] mainRegRGB = mainRegColor.replace("rgba(", "").replace("rgb(", "").replace(")", "").replace(" ", "").split(",");
        String mainCampColor = driver.findElement(By.cssSelector("#box-campaigns .product .campaign-price")).getCssValue("color");
        String[] mainCampRGB = mainCampColor.replace("rgba(", "").replace("rgb(", "").replace(")", "").replace(" ", "").split(",");

        // Обычная цена на главной странице зачеркнута
        Assert.assertEquals(("Обычная цена на главной НЕ зачеркнута"), "S", mainRegNodeName);

        // Обычная цена на главной странице серая
        Assert.assertEquals(("Обычная цена на главной НЕ серая"), mainRegRGB[0], mainRegRGB[1]);
        Assert.assertEquals(("Обычная цена на главной НЕ серая"), mainRegRGB[1], mainRegRGB[2]);

        // Акционная цена на главной жирная
        Assert.assertEquals(("Акционная цена на главной НЕ жирная"), "STRONG", mainCampNodeName);

        // Акционная цена на главной красная
        Assert.assertEquals(("Акционная цена на главной НЕ красная"), "0", mainCampRGB[1]);
        Assert.assertEquals(("Акционная цена на главной НЕ красная"), "0", mainCampRGB[2]);
        Assert.assertNotEquals(("Акционная цена на главной НЕ красная"), "0",mainCampRGB [0]);

        // Размер акционной цены на главной странице крупнее обычной
        Assert.assertTrue(("Размер акционной цены НЕ крупнее обычной"), mainRegOffsetHeight < mainCampOffsetHeight);

        driver.findElement(By.cssSelector("#box-campaigns .product")).click();
        String itemName = driver.findElement(By.cssSelector("#box-product .title")).getText();
        String itemRegularPrice = driver.findElement(By.cssSelector("#box-product .regular-price")).getText();
        String itemCampaignPrice = driver.findElement(By.cssSelector("#box-product .campaign-price")).getText();
        int itemRegOffsetHeight = Integer.parseInt (driver.findElement(By.cssSelector("#box-product .regular-price")).getAttribute("offsetHeight"));
        int itemCampOffsetHeight = Integer.parseInt (driver.findElement(By.cssSelector("#box-product .campaign-price")).getAttribute("offsetHeight"));
        String itemRegNodeName = driver.findElement(By.cssSelector("#box-product .regular-price")).getAttribute("nodeName");
        String itemCampNodeName = driver.findElement(By.cssSelector("#box-product .campaign-price")).getAttribute("nodeName");
        String itemRegColor = driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("color");
        String[] itemRegRGB = itemRegColor.replace("rgba(", "").replace("rgb(", "").replace(")", "").replace(" ", "").split(",");
        String itemCampColor = driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("color");
        String[] itemCampRGB = itemCampColor.replace("rgba(", "").replace("rgb(", "").replace(")", "").replace(" ", "").split(",");

        // Обычная цена на карточке продукта зачеркнута
        Assert.assertEquals(("Обычная цена на карточке НЕ зачеркнута"), "S", itemRegNodeName);

        // Обычная цена на карточке продукта серая
        Assert.assertEquals(("Обычная цена на карточке НЕ серая"), itemRegRGB[0], itemRegRGB[1]);
        Assert.assertEquals(("Обычная цена на карточке НЕ серая"), itemRegRGB[1], itemRegRGB[2]);

        // Акционная цена на карточке продукта жирная
        Assert.assertEquals(("Акционная цена на карточке НЕ жирная"), "STRONG", itemCampNodeName);

        // Акционная цена на карточке красная
        Assert.assertEquals(("Акционная цена на карточке НЕ красная"), "0", itemCampRGB[1]);
        Assert.assertEquals(("Акционная цена на карточке НЕ красная"), "0", itemCampRGB[2]);
        Assert.assertNotEquals(("Акционная цена на карточке НЕ красная"), "0",itemCampRGB [0]);

        // Размер акционной цены на карточке айтема крупнее обычной
        Assert.assertTrue(("Размер акционной цены НЕ крупнее обычной"), itemRegOffsetHeight < itemCampOffsetHeight);

        // Заголовок, обычная и акционная цены для товара совпадают на главной и на карточке товара
        Assert.assertEquals(("Название на главной странице и на карточке товара не совпадают"), mainPageName, itemName);
        Assert.assertEquals(("Обычная цена на главной странице и на карточке товара не совпадают"), mainPageRegularPrice, itemRegularPrice);
        Assert.assertEquals(("Акционная цена на главной странице и на карточке товара не совпадают"),mainPageCampaignPrice, itemCampaignPrice);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

