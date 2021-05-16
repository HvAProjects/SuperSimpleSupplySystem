package selenium;

import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.household.Household;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProductTests extends BaseSeleniumTests {

    @Test
    public void testAddProduct() throws InterruptedException {
        User user = loginUser();
//        {
//            WebElement element = driver.findElement(By.cssSelector(".mat-button-wrapper > .ng-star-inserted:nth-child(1)"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).perform();
//        }
        Household household = createHousehold("TestHousehold", user);
        createLocation("TestLocation", household);

        Thread.sleep(2000);
        driver.findElement(By.id("hamburger-menu")).click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector("*[ng-reflect-router-link='/household/,27']")).click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mat-drawer-backdrop")).click();
        driver.findElement(By.cssSelector(".mat-button-wrapper > span:nth-child(1)")).click();
        driver.findElement(By.id("open-scanner-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("temp-cola-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("amount")).click();
        driver.findElement(By.id("amount")).sendKeys("5");
        driver.findElement(By.id("expiration-date")).click();
        driver.findElement(By.id("expiration-date")).sendKeys("28-05-2022");
        driver.findElement(By.id("location")).click();
        driver.findElement(By.cssSelector(".mat-option-text")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("add-product-btn")).click();
        Thread.sleep(2000);
        driver.findElement(By.tagName("tr"));
    }
}
