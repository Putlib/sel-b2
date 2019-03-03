import com.google.common.collect.Ordering;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class task9 {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void Task9() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> countries = driver.findElements(By.cssSelector(".row"));
        int countriesCount = countries.size();
        ArrayList<String> actualCountryList = new ArrayList<String>();
        for (int j = 0; j < countriesCount; j++) {
            int rowNum = j + 1;
            String country = driver.findElement(By.xpath("(//*[@class = 'row'])[" + rowNum + "]//td[5]")).getText();
            actualCountryList.add(country);
        }

        Assert.assertTrue(String.format("Страны отсортированы не в алфавитном порядке"), Ordering.natural().isOrdered(actualCountryList));

        List<WebElement> zonedCountries = driver.findElements(By.xpath("//*[@class = 'row']//td[6][not(text()=0)]/../td[5]"));
        int zonedCountriesCount = zonedCountries.size();
        for (int i = 0; i < zonedCountriesCount; i++) {
            zonedCountries = driver.findElements(By.xpath("//*[@class = 'row']//td[6][not(text()=0)]/../td[5]/a"));
            zonedCountries.get(i).click();

            List<WebElement> countryZones = driver.findElements(By.xpath("//*[@type='hidden' and contains(@name, '[name]')]"));
            int countryZonesCount = countryZones.size();
            ArrayList<String> countryZonesList = new ArrayList<String>();
            for (int k = 0; k < countryZonesCount; k++) {
                String zone = driver.findElement(By.xpath("//*[@type='hidden' and contains(@name, '[name]')]")).getText();
                actualCountryList.add(zone);
            }

            Assert.assertTrue(String.format("Зоны внутри страны отсортированы не в алфавитном порядке"), Ordering.natural().isOrdered(countryZonesList));
            driver.navigate().back();
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

