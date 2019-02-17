import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest
{
    private WebDriver driver;

    @Before
    public void start () {
        driver = new ChromeDriver();
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://www.avito.ru");
    }

    @After
    public void stop () {
        driver.quit();
        driver = null;
    }
}
