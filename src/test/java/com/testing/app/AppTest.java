package com.testing.app;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest {
    // Declare WebDriver
    private ChromeDriver driver;
    int contador = 1;

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
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    @AfterMethod
    public void logout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/a"))).click();;
    }
    @BeforeMethod
    public void clearCache(){
        driver.get("http://automationpractice.com/index.php");
        if (!(driver instanceof WebStorage)) {
                throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
            }
            WebStorage webStorage = (WebStorage)driver;
            webStorage.getSessionStorage().clear();
            webStorage.getLocalStorage().clear();
    }

    // User register
    @Test
    public void TC_001_CorrectUserRegister() throws InterruptedException { 
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("es-MX"), new RandomService()
                );
        String email = fakeValuesService.bothify("????##@gmail.com");
        Assert.assertEquals(driver.getTitle(), "My Store");
        contador = contador + 1;
        Actions actions = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));

        
        // Main page
        WebElement buttonSignIn = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[2]/div/div/nav/div[1]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn)).click();

        WebElement inputEmail = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[1]/form/div/div[2]/input"));
        inputEmail.sendKeys(email);

        WebElement buttonCreateAccount = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[1]/form/div/div[3]/button"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateAccount)).click();;
        //Wait for changes
        Thread.sleep(10000);
        // Personal Information Page
        // Title
        WebElement inputGender1, inputGender2;
        inputGender1 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[1]/div[1]/label/div/span/input"));
        actions.moveToElement(inputGender1).click().perform();
        inputGender2 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[1]/div[2]/label/div/span/input"));
        actions.moveToElement(inputGender2).click().perform();

        // Name
        WebElement inputName = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[2]/input"));
        inputName.sendKeys("Lidia");

        // Lastname
        WebElement inputLastName = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[3]/input"));
        inputLastName.sendKeys("Garcia");

        // Password
        WebElement inputPassword = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[5]/input"));
        inputPassword.sendKeys("12345");

        // Date of birth
        WebElement day = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[1]/div/select"));
        day.sendKeys("12");
        day.click();

        WebElement month = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[2]/div/select"));
        month.sendKeys("October");
        month.click();

        WebElement year = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[3]/div/select"));
        year.sendKeys("2000");
        year.click();

        // Options
        WebElement newsletter, offers;
        newsletter = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[7]/div/span/input"));
        newsletter.click();
        offers = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[1]/div[8]/div/span/input"));
        offers.click();

        // ADDRESS SECTION
        // Company
        WebElement inputCompany = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[3]/input"));
        inputCompany.sendKeys("NTT DATA");

        // Address
        WebElement inputAddress = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[4]/input"));
        inputAddress.sendKeys("221B Bakerstreet");

        // Address2
        WebElement inputAddress2 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[5]/input"));
        inputAddress2.sendKeys("Another address");

        // City
        WebElement inputCity = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[6]/input"));
        inputCity.sendKeys("New York");


        // State
        WebElement state = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[7]/div/select"));
        Select select = new Select(state);
        select.selectByValue("32");

        // CP
        WebElement inputPC = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[8]/input"));
        inputPC.sendKeys("87298");

        // Country
        WebElement country = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[9]/div/select"));
        Select select2 = new Select(country);
        select2.selectByValue("21");

        // Additional Information
        WebElement inputAI = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[10]/textarea"));
        inputAI.sendKeys("Here can you write more info.");

        // Phone
        WebElement inputPhone = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[12]/input"));
        inputPhone.sendKeys("0123456789");

        // Alias address
        WebElement inputAliasAddress = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[2]/p[14]/input"));
        inputAliasAddress.sendKeys("My address.");

        // Register button
        WebElement buttonRegister = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[4]/button"));
        buttonRegister.click();

        
    }
  
    // Incorrect User  register (Empty fields)
    @Test
    public void TC_002_InorrectUserRegister() throws InterruptedException { 
        //driver.get("http://automationpractice.com/index.php");
        Assert.assertEquals(driver.getTitle(), "My Store");
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("es-MX"), new RandomService()
                );
        String email = fakeValuesService.bothify("????##@gmail.com");
        contador = contador + 1;
        Actions actions = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        // Main page
        WebElement buttonSignIn = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[2]/div/div/nav/div[1]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn)).click();

        WebElement inputEmail = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[1]/form/div/div[2]/input"));
        inputEmail.sendKeys(email);

        WebElement buttonCreateAccount = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[1]/form/div/div[3]/button"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateAccount)).click();;
        //Wait for changes pa que jale jaja 
        Thread.sleep(10000);
        // Personal Information Page
        // Register button
        WebElement buttonRegister = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/form/div[4]/button"));
        buttonRegister.click();
    }

    // User login
    @Test
    public void TC_003_CorrectUserLogin() throws InterruptedException {
        //driver.get("http://automationpractice.com/index.php");
        Assert.assertEquals(driver.getTitle(), "My Store");
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("es-MX"), new RandomService()
                );
        String email = fakeValuesService.bothify("????##@gmail.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        
        // Main page
        WebElement buttonSignIn = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn)).click();

        //Second page
        //Email
        WebElement inputEmail = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[2]/form/div/div[1]/input"));
        inputEmail.sendKeys(email);

        //Password
        WebElement inputPass = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[2]/form/div/div[2]/span/input"));
        inputPass.sendKeys("12345");
        
        //Login button
        WebElement buttonLogin = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button"));
        buttonLogin.click();
     
    }

    // Incorrect User login (Empty fields)
    @Test
    public void TC_004_CorrectUserLogin() throws InterruptedException {
        //driver.get("http://automationpractice.com/index.php");
        Assert.assertEquals(driver.getTitle(), "My Store");
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("es-MX"), new RandomService()
                );
        String email = fakeValuesService.bothify("????##@gmail.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));

         // Main page
        WebElement buttonSignIn = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn)).click();

        //Second page
        
        //Login button
        WebElement buttonLogin = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button"));
        buttonLogin.click();
     
    }

    // user navigation
    @Test
    public void TC_005_UserNavigation() throws InterruptedException {
        //driver.get("http://automationpractice.com/index.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        Assert.assertEquals(driver.getTitle(), "My Store");

        // Women
        WebElement women = driver.findElement(By.xpath("html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(women)).click();
        
        // Dresses
        WebElement dress = driver
        .findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(dress)).click();
        
        // T-shirts
        WebElement shirt = driver
        .findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(shirt)).click();

        // Search
        WebElement button = driver
        .findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/button"));
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();

        WebElement search = driver
        .findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/input[4]"));
        search.sendKeys("dress");
        search.sendKeys(Keys.ENTER);
         driver
        .findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/input[4]")).clear();
        //wait.until(ExpectedConditions.elementToBeClickable(button)).click();

        WebElement search2Element = driver
        .findElement(By.xpath("html/body/div/div[1]/header/div[3]/div/div/div[2]/form/input[4]"));
        search2Element.sendKeys("jeans");
        search2Element.sendKeys(Keys.ENTER);
        driver
        .findElement(By.xpath("html/body/div/div[1]/header/div[3]/div/div/div[2]/form/input[4]")).clear();
        //wait.until(ExpectedConditions.elementToBeClickable(button)).click();

       //wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        WebElement search3Element = driver
        .findElement(By.xpath("html/body/div/div[1]/header/div[3]/div/div/div[2]/form/input[4]"));
        //Falla aqui, no se porque no detecta el input de busqueda
        wait.until(ExpectedConditions.elementToBeClickable(search3Element)).sendKeys("BloUsE");
        driver
        .findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/button")).click();

        WebElement search4Element = driver
        .findElement(By.xpath("html/body/div/div[1]/header/div[3]/div/div/div[2]/form/input[4]"));
        search4Element.sendKeys("");
        search4Element.sendKeys(Keys.ENTER);
        //wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        
    }

    @Test
    public void TC_006_AddToCart() throws InterruptedException {
        //driver.get("http://automationpractice.com/index.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        Actions actions = new Actions(driver);
        DecimalFormat formatTotal = new DecimalFormat("#.##");
        Assert.assertEquals(driver.getTitle(), "My Store");
        Float priceFirstItem  = Float.parseFloat(driver.findElement(
                By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/div[1]/span"))
                .getText().replace("$", ""));
        WebElement addToCartI = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div"));
        actions.moveToElement(addToCartI).perform();
        WebElement addToCartB = driver.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/div[2]/a[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartB)).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/span")))
                .click();        
        Float priceSecItem  = Float.parseFloat(driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[2]/div/div[2]/div[1]/span")).getText().replace("$", ""));
        WebElement addToCartI2 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[2]/div"));
        actions.moveToElement(addToCartI2).perform();
        WebElement addToCartB2 = driver.findElement(By.xpath("//*[@id='homefeatured']/li[2]/div/div[2]/div[2]/a[1]/span"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartB2)).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span")))
                .click();
        String total = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[1]/span"))).getText().replace("$", "");
        Float totalDo = Float.parseFloat(total);
        Float totalCalculated = Float.parseFloat(formatTotal.format(priceFirstItem + priceSecItem));

        Assert.assertEquals( totalCalculated, totalDo);

    }
    @Test
    public void TC_007_RemoveFromCart() throws InterruptedException {
        //driver.get("http://automationpractice.com/index.php");
        //Set WebDriverWait, Actions and Decimal format for numbers
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(15000));
        Actions actions = new Actions(driver);
        DecimalFormat formatTotal = new DecimalFormat("#.##");
        //Check if the page name is correct
        Assert.assertEquals(driver.getTitle(), "My Store");
        //Hover on first item
        WebElement addToCartI = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div"));
        actions.moveToElement(addToCartI).perform();
        //Add first item into cart
        WebElement addToCartB = driver.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/div[2]/a[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartB)).click();
        //Click continue shopping
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/span")))
                .click();        
        //Hover second item
        WebElement addToCartI2 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[2]/div"));
        actions.moveToElement(addToCartI2).perform();
        //Add second item
        WebElement addToCartB2 = driver.findElement(By.xpath("//*[@id='homefeatured']/li[2]/div/div[2]/div[2]/a[1]/span"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartB2)).click();
        //Open cart
        WebElement cartButton  = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        //Get prices form cart table
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table")));
        String totalWithoutRemove = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tfoot/tr[1]/td[3]")).getText().replace("$", "");
        String priceFirstItemS = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr[1]/td[6]/span")).getText().replace("$", "");
        //Delete an element form cart
        WebElement deleteFromCartButton = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr[1]/td[7]/div/a"));
        deleteFromCartButton.click();
        //Wait for removal to take effect
        Thread.sleep(10000);
        //Get the total before removal
        String totalRemove = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tfoot/tr[1]/td[3]")).getText().replace("$", "");
        Float totalRe = Float.parseFloat(totalRemove);
        Float totalWiRe = Float.parseFloat(totalWithoutRemove);
        Float priceFiI = Float.parseFloat(priceFirstItemS);
        Float totalExpected = Float.parseFloat(formatTotal.format(totalWiRe - priceFiI));
        //Validation
        Assert.assertEquals(totalExpected, totalRe);


    }

    @Test
    public void TC_008_BuyCart() {
        //driver.get("http://automationpractice.com/index.php");
        //Set WebDriverWait, Actions and Decimal format for numbers
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        Actions actions = new Actions(driver);
        //Check if the page name is correct
        Assert.assertEquals(driver.getTitle(), "My Store");
        //Hover on first item
        WebElement addToCartI = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div"));
        actions.moveToElement(addToCartI).perform();
        //Add first item into cart
        WebElement addToCartB = driver.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/div[2]/a[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartB)).click();
        //Click continue shopping
        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/span")))
                .click();        
        //Hover second item
        WebElement addToCartI2 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[2]/div"));
        actions.moveToElement(addToCartI2).perform();
        //Add second item
        WebElement addToCartB2 = driver.findElement(By.xpath("//*[@id='homefeatured']/li[2]/div/div[2]/div[2]/a[1]/span"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartB2)).click();
        //Go to cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/a")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/p[2]/a[1]")
        )).click();  

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form")));
        
        WebElement mail = driver.findElement(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[1]/input")
        );
        mail.sendKeys("fuentesf.jorgealan@gmail.com");
        WebElement pass = driver.findElement(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[2]/span/input")
        );
        pass.sendKeys("123345");
        driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form")));
        //Continue
        driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/form/p/button")).click();

        WebElement termsCheck = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/div/p[2]/div/span/input"));
        actions.moveToElement(termsCheck).click().perform();

        driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/form/p/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[3]/div[1]/div/p/a"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/form")));

        driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/form/p/button")).click();

        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/p/strong"))).getText();

        Assert.assertEquals(result, "Your order on My Store is complete.");
    }

    @AfterSuite
    public void finishSuite() {
        driver.quit();
    }

}
