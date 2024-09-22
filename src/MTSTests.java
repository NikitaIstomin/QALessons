import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MTSTests {

    public WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void blockAvailability(){
        driver.get("https://www.mts.by/");
        WebElement block = driver.findElement(By.className("pay__wrapper"));
        String text1 = block.findElement(By.tagName("h2")).getText().trim();
        System.out.println(text1);
        Assert.assertEquals(text1.contains("Онлайн пополнение"), true);
        Assert.assertEquals(text1.contains("без комиссии"), true);
    }

    @Test
    public void logoAvailability(){
        driver.get("https://www.mts.by/");
        WebElement paymentPartners = driver.findElement(By.className("pay__partners"));
        String[] logoname = {
                "Visa",
                "Verified By Visa",
                "MasterCard",
                "MasterCard Secure Code",
                "Белкарт"
        };
        for (int i = 0; i < logoname.length; i++) {
            paymentPartners.findElement(By.xpath(".//img[@alt='" + logoname[i] + "']"));
        }
    }

    @Test
    public void linkAvailability(){
        driver.get("https://www.mts.by/");
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        link.click();
    }

    @Test
    public void nextButtonAvailability3() {
        driver.get("https://www.mts.by/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = driver.findElement(By.id("pay-connection"));
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
        phoneField.sendKeys("297777777");
        WebElement sumField = form.findElement(By.id("connection-sum"));
        sumField.sendKeys("100");
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.button__default")));
        next.click();

    }

    @AfterTest
    public void closeConnection(){
        driver.quit();
    }
}
