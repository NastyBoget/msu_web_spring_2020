package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import java.util.*;

public class Testing {
    private final String chromeDriver = "/Users/anastasiabogatenkova/sportinfo/lib/chromedriver";
    protected final String appURL = "http://localhost:8080/index";
    protected WebDriver driver;

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        driver = new ChromeDriver();
        driver.get(appURL);
        driver.manage().window().setSize(new Dimension(1200, 767));
        driver.findElement(By.linkText("Competitions")).click();
        driver.findElement(By.id("Name")).click();
        driver.findElement(By.id("Name")).sendKeys("fifa");
        {
            List<WebElement> elements = driver.findElements(By.linkText("FIFA World Cup"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.id("Time")).click();
        driver.findElement(By.id("Time")).sendKeys("2019");
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(3) > td:nth-child(2)"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.id("Location")).click();
        driver.findElement(By.id("Location")).sendKeys("Russia");
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(3) > td:nth-child(3)"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.id("SportKind")).click();
        driver.findElement(By.id("SportKind")).sendKeys("football");
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(3) > td:nth-child(4)"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Add competition")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("World cup");
        driver.findElement(By.id("location")).click();
        driver.findElement(By.id("location")).sendKeys("London");
        driver.findElement(By.id("time")).click();
        driver.findElement(By.id("time")).sendKeys("2020-02-03 12:00:00");
        {
            WebElement dropdown = driver.findElement(By.id("sportsmen"));
            dropdown.findElement(By.xpath("//option[. = 'Auston Matthews']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.id("teams"));
            dropdown.findElement(By.xpath("//option[. = 'None']")).click();
        }
        driver.findElement(By.id("numSeats")).click();
        driver.findElement(By.id("numSeats")).sendKeys("100");
        driver.findElement(By.id("price")).click();
        driver.findElement(By.id("price")).sendKeys("1000");
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[2]")).sendKeys("12");
        driver.findElement(By.id("competitionForm")).click();
        driver.findElement(By.xpath("(//input[@id=\'price\'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id=\'price\'])[2]")).sendKeys("1000");
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[3]")).click();
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[3]")).sendKeys("0");
        driver.findElement(By.xpath("(//input[@id=\'price\'])[3]")).click();
        driver.findElement(By.xpath("(//input[@id=\'price\'])[3]")).sendKeys("0");
        driver.findElement(By.cssSelector("input:nth-child(42)")).click();
        driver.findElement(By.linkText("World cup")).click();
        driver.findElement(By.linkText("Edit competition")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("World cup");
        driver.findElement(By.id("location")).click();
        driver.findElement(By.id("location")).sendKeys("London");
        driver.findElement(By.id("time")).click();
        driver.findElement(By.id("time")).sendKeys("2020-02-03 12:00:00");
        driver.findElement(By.id("sPoints")).click();
        driver.findElement(By.id("sPoints")).sendKeys("200");
        driver.findElement(By.id("sPlace")).click();
        driver.findElement(By.id("sPlace")).sendKeys("1");
        driver.findElement(By.cssSelector("input:nth-child(23)")).click();
        driver.findElement(By.linkText("Delete competition")).click();
        driver.findElement(By.linkText("sportsmen")).click();
        driver.findElement(By.linkText("Add sportsman")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Jack Green");
        driver.findElement(By.id("birthday")).click();
        driver.findElement(By.id("birthday")).sendKeys("1990-04-04");
        driver.findElement(By.id("trainer")).click();
        {
            WebElement dropdown = driver.findElement(By.id("trainer"));
            dropdown.findElement(By.xpath("//option[. = 'James van rimsdike']")).click();
        }
        driver.findElement(By.id("team")).click();
        {
            WebElement dropdown = driver.findElement(By.id("team"));
            dropdown.findElement(By.xpath("//option[. = 'None']")).click();
        }
        driver.findElement(By.cssSelector("input:nth-child(13)")).click();
        driver.findElement(By.linkText("Jack Green")).click();
        driver.findElement(By.linkText("Edit sportsman")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Jack Green");
        driver.findElement(By.id("birthday")).click();
        driver.findElement(By.id("birthday")).sendKeys("1990-04-04");
        driver.findElement(By.id("team")).click();
        {
            WebElement dropdown = driver.findElement(By.id("team"));
            dropdown.findElement(By.xpath("//option[. = 'None']")).click();
        }
        driver.findElement(By.cssSelector("input:nth-child(14)")).click();
        driver.findElement(By.linkText("Delete sportsman")).click();
        driver.findElement(By.linkText("teams")).click();
        driver.findElement(By.linkText("Add team")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Team");
        driver.findElement(By.id("trainer")).click();
        {
            WebElement dropdown = driver.findElement(By.id("trainer"));
            dropdown.findElement(By.xpath("//option[. = 'Roope Hinz']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.id("sportsmen"));
            dropdown.findElement(By.xpath("//option[. = 'Dominic Kubalik']")).click();
        }
        driver.findElement(By.cssSelector("input:nth-child(11)")).click();
        driver.findElement(By.linkText("Team")).click();
        driver.findElement(By.linkText("Edit team")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Team");
        {
            WebElement dropdown = driver.findElement(By.id("sportsmen"));
            dropdown.findElement(By.xpath("//option[. = 'William Nyulander']")).click();
        }
        driver.findElement(By.cssSelector("input:nth-child(12)")).click();
        driver.findElement(By.linkText("Delete team")).click();
        driver.findElement(By.linkText("competitions")).click();
        driver.findElement(By.linkText("2020 GYMNASTICS WORLD CUP")).click();
        driver.findElement(By.linkText("More information about free seats and prices")).click();
        driver.findElement(By.linkText("Buy ticket")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("user@gmail.com");
        driver.findElement(By.id("card")).click();
        driver.findElement(By.id("card")).sendKeys("1111");
        driver.findElement(By.cssSelector("input:nth-child(9)")).click();
        driver.findElement(By.linkText("home page")).click();
        driver.quit();
    }
}
