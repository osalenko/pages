package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

/**
 * Created by SANYA on 5/20/2017.
 */
public class HomePage {

    private WebDriver driver;


    public HomePage(WebDriver driver) {
        this.driver = driver;
       }

    private By searchfield = By.name("q");
    private By searchButton = By.id("_fZl");
    private By firstLink = By.id("vs0p1c0");
    private By nextButton = By.xpath("//*[@id='pnnext']/span[2]");
    boolean console = true;


    public void doSearchAutomation(String searchKeyword)
    // fill search field by request and click "Search" button.
    {
        driver.findElement(searchfield).sendKeys(searchKeyword);
        Reporter.log("Fill search field", console);
        driver.findElement(searchButton).click();
        Reporter.log("Click search button", console);
    }

    public boolean openLinkAssert(String expectedTitle) {
        driver.findElement(firstLink).click();
        Reporter.log("Open first Link", true);
        String actualTitle = driver.getTitle();
        Reporter.log("Get title to verify", true);
        if (
            actualTitle.toLowerCase().contains(expectedTitle)) {
            Reporter.log("The actual title is - " + actualTitle,true);
            Reporter.log("The expected title is - " + expectedTitle,true);
            return true;
        } else
            {
            Reporter.log("The actual title is - " + actualTitle,true);
            Reporter.log("The expected title is - " + expectedTitle,true);
            return false;
        }
        //  assertEquals(expectedTitle, actualTitle.toLowerCase());

    }

    public boolean expectDomain(String domain)
    // Verify if expected domain is displayed in search results on pages from 1 to 5.
    {
        for (int i =1; i < 5; i++) {
            Reporter.log("Search domain on page " + i, console);
            if (driver.getPageSource().contains(domain)) {
                System.out.println("Link on page " + i);
                return true;
            } else {
                System.out.println("Text is absent on page " + i);
                Reporter.log("Click next button", console);

                WebDriverWait wait = new WebDriverWait(driver, 30, 500);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("flyr")));
                driver.findElement(nextButton).click();

                /* try {
                    Thread.sleep(1000);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }*/

            }
        } return false;
    }

    public String generator()
    {
        String alphabet= "QWERTYUIOPLKJHGFDSAZXC1234567890VBNMabcdefghijklmnopqrstuvwxyz";
        String gLogin = "";
        Random random = new Random();
        int randomLen = 6+random.nextInt(10);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(20));
            gLogin+=c;
        }
        String gEmail=gLogin+"@yopmail.com";
        return gLogin;
    }

    public void fillRegForm() throws InterruptedException {
        String firstName = generator();
        WebElement name = driver.findElement(By.id("jform_name"));
        Reporter.log("Fill username", console);
        name.sendKeys(firstName);
        String login = generator();
        WebElement userName = driver.findElement(By.id("jform_username"));
        Reporter.log("Fill second Sername", console);
        userName.sendKeys(login);
        WebElement passw = driver.findElement(By.id("jform_password2"));
        Reporter.log("fill password field", console);
        passw.sendKeys("123123qq");
        WebElement passwR = driver.findElement(By.id("jform_password1"));
        Reporter.log("repit password", console);
        passwR.sendKeys("123123qq");
        String email = generator();
        WebElement post = driver.findElement(By.id("jform_email1"));
        Reporter.log("Fill email field", console);
        post.sendKeys(email+"@yopmail.com");
        Reporter.log("repeat email", console);
        WebElement post2 = driver.findElement(By.id("jform_email2"));
        post2.sendKeys(email+"@yopmail.com");

        Thread.sleep(5000);


    }
    public void checkBoxDefSelTrue(String xpath) throws InterruptedException
    {

        WebElement chB = driver.findElement(By.xpath(xpath));
        Thread.sleep(3000);
        boolean status2= chB.isDisplayed();
        System.out.println("CheckBox is Displayed >>"+status2);
        boolean enabled_status2=chB.isEnabled();
        System.out.println("CheckBox is Enabled >>"+enabled_status2);
        boolean selected_status2=chB.isSelected();
        System.out.println("CeckBox is Selected >>"+selected_status2);
       // assertEquals(selected_status2,chB.isSelected());
        assertTrue(chB.isSelected());
        chB.click();
        assertFalse(chB.isSelected());
        boolean selected_status_new2=chB.isSelected();
        System.out.println("CheckBox is Selected after click  >>"+selected_status_new2);
        Thread.sleep(4000);
    }


    public void checkBoxDefSelFalse(String xpath) throws InterruptedException
    {

        WebElement chB = driver.findElement(By.xpath(xpath));
        boolean status= chB.isDisplayed();
        System.out.println("CheckBox is Displayed >>"+status);
        boolean enabled_status=chB.isEnabled();
        System.out.println("CheckBox is Enabled >>"+enabled_status);
        boolean selected_status=chB.isSelected();
        System.out.println("CeckBox is Selected >>"+selected_status);
        // assertEquals(selected_status2,chB.isSelected());
        assertFalse(chB.isSelected());
        chB.click();
        assertTrue(chB.isSelected());
        boolean selected_status_new=chB.isSelected();
        System.out.println("CheckBox is Selected after click  >>"+selected_status_new);
    }

}


