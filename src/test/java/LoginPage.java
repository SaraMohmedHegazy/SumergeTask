import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LoginPage {

    WebDriver driver ;
    @BeforeTest
    public void OpenBrowser() throws InterruptedException {
//            System.setProperty("webdriver.chrome.driver",
//                    "C:\\Users\\Two Star\\IdeaProjects\\SumergeTask\\src\\main\\resources\\chromedriver.exe");
           driver = new ChromeDriver();
            driver.navigate().to("https://www.saucedemo.com/");
            driver.manage().window().maximize();
           Thread.sleep(5000);
    }
    @Test(priority = 1)
    public void TestUsernameFieldPesence(){
        WebElement UsernameField = driver.findElement(By.id("user-name"));
        Assert.assertTrue(UsernameField.isDisplayed(),"Username Field is not Present");
    }
    @Test(priority = 2)
    public void TestPasswordFieldPesence(){
        WebElement PasswordField = driver.findElement(By.id("password"));
        Assert.assertTrue(PasswordField.isDisplayed(),"Password Field is not Present");
    }
    @Test(priority = 3)
    public void TestLoginButtonPesence(){
        WebElement PasswordField = driver.findElement(By.id("login-button"));
        Assert.assertTrue(PasswordField.isDisplayed(),"Login Button is not Present");
    }
    @Test(priority = 4)
    public void TestLoginwithValidData(){
         driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("app_logo")).isDisplayed(),"The 'Swag Labs' text is not displayed");
    }
    @Test(priority = 5)
    public void TestLoginwithInValidData(){
        driver.navigate().back();
        driver.findElement(By.id("user-name")).sendKeys("Ahmed");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("error-message-container")).isDisplayed());

    }
    @Test(priority = 6)
    public void TestLoginwithEmptyUsernameFiled(){
        driver.navigate().back();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement ErrorMessage = driver.findElement(By.className("error-message-container"));
        String ExpectedErrorMessage = "Epic sadface: Username is required";
        Assert.assertTrue(ErrorMessage.getText().contains(ExpectedErrorMessage),("The expected error message for empty username is not displayed."));
    }
    @Test(priority = 7)
    public void TestLoginwithEmptyPasswordFiled(){
        driver.navigate().back();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        WebElement ErrorMessage = driver.findElement(By.className("error-message-container"));
        String ExpectedErrorMessage = "Epic sadface: Password is required";
        Assert.assertTrue(ErrorMessage.getText().contains(ExpectedErrorMessage));

    }
        @AfterTest
        public void TearDown(){
            driver.quit();
        }



}
