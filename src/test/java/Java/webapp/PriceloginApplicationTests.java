package Java.webapp;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

import static oshi.util.Util.sleep;

//Start application first, then run testng.xml file in test/resources folder
//It is hard to configure SpringBootTest to work properly with testng.xml
@SpringBootTest
public class PriceloginApplicationTests {
    WebDriver driver;

    @org.junit.jupiter.api.Test
    void contextLoads() {
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.get("http://localhost:8080");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("vaadinLoginUsername")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("vaadinLoginPassword")));
    }

    @Test(priority = 1)
    public void testValidLogin() {
        WebElement usernameField = driver.findElement(By.id("vaadinLoginUsername"));
        WebElement passwordField = driver.findElement(By.id("vaadinLoginPassword"));
        WebElement loginButton = driver.findElement(By.tagName("vaadin-button"));

        usernameField.sendKeys("shern");
        passwordField.sendKeys("2004");
        loginButton.click();
        sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/main");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        WebElement usernameField = driver.findElement(By.id("vaadinLoginUsername"));
        WebElement passwordField = driver.findElement(By.id("vaadinLoginPassword"));
        WebElement loginButton = driver.findElement(By.tagName("vaadin-button"));

        usernameField.sendKeys("se");
        passwordField.sendKeys("wrongpassword");
        loginButton.click();
        sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/login");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
