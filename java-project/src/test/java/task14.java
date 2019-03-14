import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class task14
{
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start () {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Task14() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("a.button")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        String mainWindow = driver.getWindowHandle();
        int numLinks = links.size();
        for (int i=0; i < numLinks; i ++ ){
            links = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
            links.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            for(String newWindow : driver.getWindowHandles()) {
                if (!mainWindow.equals(newWindow)) {
                    driver.switchTo().window(newWindow);
                    break;
                }
            }
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    @After
    public void stop () {
        driver.quit();
        driver = null;
    }
}
