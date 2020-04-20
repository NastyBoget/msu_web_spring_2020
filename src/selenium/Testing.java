package selenium;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
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

    @Test()
    public void pagesTest() {
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        driver = new ChromeDriver();
        driver.get(appURL);
        driver.manage().window().setSize(new Dimension(1200, 767));
        {
            List<WebElement> elements = driver.findElements(By.linkText("Competitions"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.linkText("Sportsmen"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.linkText("Teams"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Competitions")).click();
        driver.findElement(By.id("Name")).click();
        {
            WebElement element = driver.findElement(By.id("Name"));
            Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
            assertTrue(isEditable);
        }
        driver.findElement(By.id("Name")).sendKeys("fifa");
        {
            List<WebElement> elements = driver.findElements(By.linkText("FIFA World Cup"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.linkText("Add competition"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Add competition")).click();
        {
            WebElement element = driver.findElement(By.id("name"));
            Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
            assertTrue(isEditable);
        }
        driver.findElement(By.id("name")).sendKeys("Test");
        driver.findElement(By.id("location")).click();
        driver.findElement(By.id("location")).sendKeys("Test");
        driver.findElement(By.id("time")).click();
        driver.findElement(By.id("time")).sendKeys("2020-02-03 12:00:00");
        {
            WebElement dropdown = driver.findElement(By.id("sportsmen"));
            dropdown.findElement(By.xpath("//option[. = 'David Pastrniak']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.id("teams"));
            dropdown.findElement(By.xpath("//option[. = 'None']")).click();
        }
        driver.findElement(By.id("numSeats")).click();
        driver.findElement(By.id("numSeats")).sendKeys("1");
        driver.findElement(By.id("price")).click();
        driver.findElement(By.id("price")).sendKeys("200");
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[2]")).sendKeys("0");
        driver.findElement(By.xpath("(//input[@id=\'price\'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id=\'price\'])[2]")).sendKeys("0");
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[3]")).click();
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[3]")).sendKeys("0");
        driver.findElement(By.xpath("(//input[@id=\'price\'])[3]")).click();
        driver.findElement(By.xpath("(//input[@id=\'price\'])[3]")).sendKeys("0");
        driver.findElement(By.cssSelector("input:nth-child(42)")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Test"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Test")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("More information about free seats and prices"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.linkText("Delete competition"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.linkText("Edit competition"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Edit competition")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Test");
        driver.findElement(By.id("location")).click();
        driver.findElement(By.id("location")).sendKeys("Test");
        driver.findElement(By.id("time")).click();
        driver.findElement(By.id("time")).sendKeys("2020-02-03 12:00:00");
        driver.findElement(By.id("sPoints")).click();
        driver.findElement(By.id("sPoints")).sendKeys("200");
        driver.findElement(By.id("sPlace")).click();
        driver.findElement(By.id("sPlace")).sendKeys("1");
        driver.findElement(By.cssSelector("input:nth-child(23)")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tbody:nth-child(2) td:nth-child(2)"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Delete competition")).click();
        driver.findElement(By.linkText("Add competition")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Test");
        driver.findElement(By.id("location")).click();
        driver.findElement(By.id("location")).sendKeys("Test");
        driver.findElement(By.id("time")).click();
        driver.findElement(By.id("time")).sendKeys("2020-02-03 12:00:00");
        {
            WebElement dropdown = driver.findElement(By.id("sportsmen"));
            dropdown.findElement(By.xpath("//option[. = 'Artemy Panarin']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.id("teams"));
            dropdown.findElement(By.xpath("//option[. = 'None']")).click();
        }
        driver.findElement(By.id("numSeats")).click();
        driver.findElement(By.id("numSeats")).sendKeys("2");
        driver.findElement(By.id("price")).click();
        driver.findElement(By.id("price")).sendKeys("200");
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[2]")).sendKeys("0");
        driver.findElement(By.xpath("(//input[@id=\'price\'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id=\'price\'])[2]")).sendKeys("0");
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[3]")).click();
        driver.findElement(By.xpath("(//input[@id=\'numSeats\'])[3]")).sendKeys("0");
        driver.findElement(By.xpath("(//input[@id=\'price\'])[3]")).click();
        driver.findElement(By.xpath("(//input[@id=\'price\'])[3]")).sendKeys("0");
        driver.findElement(By.cssSelector("input:nth-child(42)")).click();
        driver.findElement(By.linkText("Test")).click();
        assertThat(driver.findElement(By.cssSelector("td > div:nth-child(1)")).getText(), is("there are free seats"));
        driver.findElement(By.linkText("More information about free seats and prices")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Buy ticket"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(2)"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Buy ticket")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("user@gmail.com");
        driver.findElement(By.id("card")).click();
        driver.findElement(By.id("card")).sendKeys("1111");
        driver.findElement(By.cssSelector("input:nth-child(9)")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td > div:nth-child(1)"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("More information about free seats and prices")).click();
        assertThat(driver.findElement(By.cssSelector("td:nth-child(2)")).getText(), is("1"));
        {
            List<WebElement> elements = driver.findElements(By.linkText("Buy ticket"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Buy ticket")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("user@gmail.com");
        driver.findElement(By.id("card")).click();
        driver.findElement(By.id("card")).sendKeys("1111");
        driver.findElement(By.cssSelector("input:nth-child(9)")).click();
        assertThat(driver.findElement(By.cssSelector("div:nth-child(2)")).getText(), is("no free seats"));
        driver.findElement(By.linkText("competitions")).click();
        driver.findElement(By.linkText("Test")).click();
        driver.findElement(By.linkText("Delete competition")).click();
        driver.findElement(By.linkText("sportsmen")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Add sportsman"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Add sportsman")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Test");
        driver.findElement(By.id("birthday")).click();
        driver.findElement(By.id("birthday")).sendKeys("1990-04-04");
        driver.findElement(By.cssSelector("input:nth-child(13)")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Test"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Test")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Delete sportsman"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.linkText("Edit sportsman"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Edit sportsman")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Test");
        driver.findElement(By.id("birthday")).click();
        driver.findElement(By.id("birthday")).sendKeys("1990-04-04");
        driver.findElement(By.id("trainer")).click();
        {
            WebElement dropdown = driver.findElement(By.id("trainer"));
            dropdown.findElement(By.xpath("//option[. = 'Roope Hinz']")).click();
        }
        driver.findElement(By.cssSelector("input:nth-child(14)")).click();
        driver.findElement(By.linkText("Delete sportsman")).click();
        driver.findElement(By.linkText("teams")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Add team"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Add team")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Test");
        {
            WebElement dropdown = driver.findElement(By.id("sportsmen"));
            dropdown.findElement(By.xpath("//option[. = 'David Pastrniak']")).click();
        }
        driver.findElement(By.cssSelector("input:nth-child(11)")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Test"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Test")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Delete team"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.linkText("Edit team"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Edit team")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Test");
        {
            WebElement dropdown = driver.findElement(By.id("sportsmen"));
            dropdown.findElement(By.xpath("//option[. = 'Patrick Layne']")).click();
        }
        driver.findElement(By.cssSelector("input:nth-child(12)")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("Patrick Layne"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Delete team")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("home page"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("home page")).click();
        driver.quit();
    }

    @Test(dependsOnMethods = "pagesTest")
    public void wrongTest() {
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        driver = new ChromeDriver();
        driver.get(appURL);
        driver.manage().window().setSize(new Dimension(1200, 767));
        driver.findElement(By.linkText("Competitions")).click();
        driver.findElement(By.linkText("Add competition")).click();
        driver.findElement(By.cssSelector("input:nth-child(42)")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Sorry, some error occured :("));
        driver.findElement(By.linkText("competitions")).click();
        driver.findElement(By.linkText("Add competition")).click();
        driver.findElement(By.id("time")).click();
        driver.findElement(By.id("time")).sendKeys("2020-02-03 12:00");
        driver.findElement(By.cssSelector("input:nth-child(42)")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Sorry, some error occured :("));
        driver.findElement(By.linkText("competitions")).click();
        driver.findElement(By.linkText("Add competition")).click();
        driver.findElement(By.id("time")).click();
        driver.findElement(By.id("time")).sendKeys("2020-02-03 12:00:00");
        driver.findElement(By.cssSelector("input:nth-child(42)")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Sorry, some error occured :("));
        driver.findElement(By.linkText("sportsmen")).click();
        driver.findElement(By.linkText("Add sportsman")).click();
        driver.findElement(By.cssSelector("input:nth-child(13)")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Sorry, some error occured :("));
        driver.findElement(By.linkText("sportsmen")).click();
        driver.findElement(By.linkText("Add sportsman")).click();
        driver.findElement(By.id("birthday")).click();
        driver.findElement(By.id("birthday")).sendKeys("1990-04-");
        driver.findElement(By.cssSelector("input:nth-child(13)")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Sorry, some error occured :("));
        driver.quit();
    }
}
