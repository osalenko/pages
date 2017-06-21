package seleniumGrid;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by SANYA on 6/21/2017.
 */
public class TestGridClass
{
    public WebDriver driver;
    public String URL, Node;
    protected ThreadLocal<RemoteWebDriver> threadDriver = null;

    @Parameters("browser")
    @BeforeTest
    public void launchBrowser(String browser) throws MalformedURLException {
        String URL = "http://www.calculator.net";

        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println(" Executing on FireFox");
            String Node = "http://192.168.1.6:5555/wd/hub";
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");

            driver = new RemoteWebDriver(new URL(Node), cap);
            // Puts an Implicit wait, Will wait for 10 seconds before throwing
            // exception
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.out.println(" Executing on CHROME");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            String Node = "http://192.168.1.6:5555/wd/hub";
            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("ie")) {
            System.out.println(" Executing on IE");
            DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
            cap.setBrowserName("ie");
            String Node = "http://192.168.1.6:5555/wd/hub";
            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
        } else {
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }
    }

    @Test
    public void calculatepercent() {
        // Click on Math Calculators
        driver.findElement(By.xpath("//a[contains(text(),'Math')]")).click();
        // Click on Percent Calculators
        driver.findElement(
                By.xpath("//a[contains(text(),'Percentage Calculator')]"))
                .click();
        // Enter value 17 in the first number of the percent Calculator
        driver.findElement(By.id("cpar1")).sendKeys("17");
        // Enter value 35 in the second number of the percent Calculator
        driver.findElement(By.id("cpar2")).sendKeys("35");

        // Click Calculate Button
        driver.findElement(
                By.xpath("(//input[contains(@value,'Calculate')])[1]")).click();
        // Get the Result Text based on its xpath
        String result = driver.findElement(
                By.xpath(".//*[@id='content']/p[2]/font/b")).getText();
        // Print a Log In message to the screen
        System.out.println(" The Result is " + result);
        if (result.equals("5.95")) {
            System.out.println(" The Result is Pass");
        } else {
            System.out.println(" The Result is Fail");
        }
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
