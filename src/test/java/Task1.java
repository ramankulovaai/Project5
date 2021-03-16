import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Task1 {

    WebDriver driver;
    Actions actions;


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        actions=new Actions(driver);
    }
    @Parameters({"userName", "Password"})
    @Test()
    public void test1(String username, String password) throws InterruptedException {
        driver.get("http://icehrm-open.gamonoid.com/login.php?");
        WebElement usernameInput=driver.findElement(By.id("username"));
        usernameInput.sendKeys(username);
        WebElement passwordInput=driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton=driver.findElement(By.xpath("//button[@onclick='submitLogin();return false;']"));
        loginButton.click();
        WebElement switchButton=driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-new-window']"));
         switchButton.click();
         Thread.sleep(1000);
         WebElement textValidation=driver.findElement(By.xpath("//h4[@id='myModalLabel']/parent::div"));
        Assert.assertEquals(textValidation.getText(),"Switch Employee");
       WebElement text2=driver.findElement(By.cssSelector("#profileSwitchModal .modal-body p"));
       Assert.assertEquals(text2.getText(), "Select The Employee to Switch Into");
     WebElement firstName=driver.findElement(By.xpath("//div[@id='s2id_switch_emp']/child::a"));
     firstName.click();
       Thread.sleep(1000);
//        Select dropDown=new Select(firstName);
//        dropDown.selectByVisibleText("Lala Lamees");

        WebElement lalaLamees = driver.findElement(By.xpath("//li[@class ='select2-results-dept-0 select2-result" +
                " select2-result-selectable'] //*[contains(text(), 'Lala Lamees')]"));
        lalaLamees.click();
        WebElement switchClick=driver.findElement(By.xpath("//div[@id='profileSwitchModal']//button[@class='btn btn-primary']"));
        switchClick.click();
        Thread.sleep(2000);
        WebElement firstAndLastName=driver.findElement(By.xpath("//section[@class='sidebar']/div[@class='user-panel'][2]//p/a"));
       Assert.assertTrue(firstAndLastName.isDisplayed());
       Assert.assertEquals("Lala Lamees", firstAndLastName.getText() );
       WebElement changeText=driver.findElement(By.xpath("//section[@class='sidebar']/div[@class='user-panel'][2]//p/following-sibling::a"));
       Assert.assertEquals(changeText.getText(),"Changed To");
       firstAndLastName.click();
       String persInfo=driver.getCurrentUrl();
        System.out.println(persInfo);
       Assert.assertTrue(persInfo.contains("Personal_Information"));
       String title=driver.getTitle();
       Thread.sleep(2000);
       WebElement jobDetails=driver.findElement(By.xpath("//div[@class='ant-card-head']//*[.='Job Details']"));
       Assert.assertEquals(jobDetails.getText(), "Job Details");







    }



}
