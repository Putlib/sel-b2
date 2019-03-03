import com.google.common.collect.Ordering;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class task9_2 {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void Task9_2() {
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> countries = driver.findElements(By.cssSelector(".row"));
        int countriesCount = countries.size();
        for (int i = 0; i < countriesCount; i++) {
            countries = driver.findElements(By.xpath("//*[@title = 'Edit']"));
            countries.get(i).click();

            List<WebElement> countryZones = driver.findElements(By.xpath("//*[contains(@name, 'zone_code')]//option [@selected]"));
            int countryZonesCount = countryZones.size();
            ArrayList<String> countryZonesList = new ArrayList<String>();
            for (int j = 0; j < countryZonesCount; j++) {
                int countryZoneNum = j + 1;
                String countryZone = driver.findElement(By.xpath("(//*[contains(@name, 'zone_code')]//option [@selected])["+countryZoneNum+"]")).getText();
                countryZonesList.add(countryZone);
            }
            Assert.assertTrue(String.format("Страны отсортированы не в алфавитном порядке"), Ordering.natural().isOrdered(countryZonesList));
            driver.navigate().back();

        }
        }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

