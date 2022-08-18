package com.testing.app;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
//Añadí librerias faltantes, no reconoce algunas (Lidia)
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest {
    // Declare WebDriver
    private ChromeDriver driver;

    @BeforeSuite
    public void configSuite() {
        // Load properties from test.properties on src/test/resources/ directory
        Properties props = new Properties();
        // Try catch for reading properties file
        try {
            // load properties file
            props.load(new FileInputStream(new File("src/test/resources/test.properties")));
            // set webdriver system property
            System.setProperty(props.get("driver_name").toString(),
                    props.get("driver_path").toString());
            // init driver
            driver = new ChromeDriver();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // User register
    @Test
    public void TC_001_UserRegister() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        Assert.assertEquals(driver.getTitle(), "My Store");

        // Main page
        WebElement buttonSignIn = driver
                .findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a"));
        buttonSignIn.click();

        //
        WebElement inputEmail = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[1]/form/div/div[2]/input"));
        inputEmail.sendKeys("ivaanerygarcia@gmail.com");

        WebElement buttonCreateAccount = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[1]/form/div/div[3]/button"));
        buttonCreateAccount.click();

        // Personal Information Page
        // Title
        WebElement inputGender1, inputGender2;
        inputGender1 = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[1]/div[1]/label"));
        inputGender1.click();
        inputGender2 = driver
                .findElement(By.id("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[1]/div[2]/label"));
        inputGender2.click();

        // Name
        WebElement inputName = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[2]/input"));
        inputName.sendKeys("Lidia");

        // Lastname
        WebElement inputLastName = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[3]/input"));
        inputLastName.sendKeys("Garcia");

        // Password
        WebElement inputPassword = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[5]/input"));
        inputPassword.sendKeys("12345");

        // Date of birth
        WebElement day = driver.findElement(
                By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[1]/div/select"));
        day.sendKeys("12");
        day.click();

        WebElement month = driver.findElement(
                By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[2]/div/select"));
        month.sendKeys("October");
        month.click();

        WebElement year = driver.findElement(
                By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[3]/div/select"));
        year.sendKeys("2000");
        year.click();

        // Options
        WebElement newsletter, offers;
        newsletter = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[7]/div/span/input"));
        newsletter.click();
        offers = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[8]/div/span/input"));
        offers.click();

        // ADDRESS SECTION
        // Company
        WebElement inputCompany = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[3]/input"));
        inputCompany.sendKeys("NTT DATA");

        // Address
        WebElement inputAddress = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[4]/input"));
        inputAddress.sendKeys("221B Bakerstreet");

        // Address2
        WebElement inputAddress2 = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[5]/input"));
        inputAddress2.sendKeys("Another address");

        // City
        WebElement inputCity = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[6]/input"));
        inputCity.sendKeys("New York");

        // State
        WebElement state = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[7]/div/span"));
        state.click();

        // CP
        WebElement inputPC = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[8]/input"));
        inputPC.sendKeys("87298");

        // Country
        WebElement country = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[9]/div/span"));
        country.click();

        // Additional Information
        WebElement inputAI = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[10]/textarea"));
        inputAI.sendKeys("Here can you write more info.");

        // Phone
        WebElement inputPhone = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[12]/input"));
        inputPhone.sendKeys("0123456789");

        // Alias address
        WebElement inputAliasAddress = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[14]/input"));
        inputAliasAddress.sendKeys("My address.");

        // Register button
        WebElement buttonRegister = driver
                .findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[4]/button"));
        buttonRegister.click();
    }

    // User login
    @Test
    public void TC_002_UserLogin() throws InterruptedException {

    }

    // user navigation
    @Test
    public void TC_005_UserNavigation() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        Assert.assertEquals(driver.getTitle(), "My Store");

        // Women
        WebElement women = driver.findElement(By.xpath("html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/a"));
        women.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2000));

        // Dresses
        WebElement dress = driver
                .findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a"));
        dress.click();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMillis(2000));

        // T-shirts
        WebElement tshirt = driver
                .findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a"));
        tshirt.click();
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofMillis(2000));

    }

    @Test
    public void TC_006_AddToCart() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        Assert.assertEquals(driver.getTitle(), "My Store");
        WebElement addToCartButton = driver
                .findElement(By.xpath("//*[@id='homefeatured']/li[1]/div/div[2]/div[2]/a[1]"));
        addToCartButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span")))
                .click();
    }

    @Test
    public void TC_007_BuyCart() {

    }

    @AfterSuite
    public void finishSuite() {
        driver.quit();
    }

}
