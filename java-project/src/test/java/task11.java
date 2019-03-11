import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Instant;


public class task11
{
    private WebDriver driver;

    @Before
    public void start () {
        driver = new ChromeDriver();
    }

    @Test
    public void Task11() {
        driver.navigate().to("http://localhost/litecart/en/");

        long unixTimestamp = Instant.now().getEpochSecond();
        String email = unixTimestamp + "@gmail.com";

        driver.findElement(By.cssSelector("td a[href$='create_account']")).click();
        driver.findElement(By.name("firstname")).sendKeys("Test");
        driver.findElement(By.name("lastname")).sendKeys("Testtest");
        driver.findElement(By.name("address1")).sendKeys("Big str. 67-90");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("Rivia");
        driver.findElement(By.cssSelector(".select2-selection")).click();
        driver.findElement(By.cssSelector("[value='US']")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+19876543210");
        driver.findElement(By.name("password")).sendKeys( "qwerty123");
        driver.findElement(By.name("confirmed_password")).sendKeys( "qwerty123");
        driver.findElement(By.name("create_account")).click();

        driver.findElement(By.cssSelector("td a[href$='/logout']")).click();

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("qwerty123");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("td a[href$='/logout']")).click();
    }


    @After
    public void stop () {
        driver.quit();
        driver = null;
    }
}
