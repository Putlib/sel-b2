import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static java.lang.Thread.sleep;

public class task7
{
    private WebDriver driver;

    @Before
    public void start () {
        driver = new ChromeDriver();
    }

    @Test
    public void Task7() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> elementsToClick = driver.findElements(By.id("app-"));
        int elementCount = elementsToClick.size();
        for (int i = 0; i < elementCount; i++){
            elementsToClick = driver.findElements(By.id("app-"));
            elementsToClick.get(i).click();
            driver.findElement(By.cssSelector("h1")).isDisplayed();

            List<WebElement> insideElementsToClick = driver.findElements(By.cssSelector("ul.docs a"));
            int insideElementCount = insideElementsToClick.size();
            for (int j = 0; j < insideElementCount; j++){
                insideElementsToClick = driver.findElements(By.cssSelector("ul.docs a"));
                insideElementsToClick.get(j).click();
                driver.findElement(By.cssSelector("h1")).isDisplayed();
            }
        }
    }

    @After
    public void stop () {
        driver.quit();
        driver = null;
    }
}
