package pages;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Optional;

/**
 * Created by SANYA on 5/20/2017.
 */
public class Setup {

    //CrossBrowser testing method

    @Parameters({"browser"})
    public static WebDriver CrossBrows(@Optional("chrome") String browser, String address, String environment) {

        WebDriver driver = null;
        if (browser.equalsIgnoreCase("firefox"))
        {
            FirefoxDriverManager.getInstance().setup();
           // System.setProperty("webdriver.gecko.driver", "C:\\Users\\SANYA\\Downloads\\geckodriver.exe");
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("ie")) {
            InternetExplorerDriverManager.getInstance().setup();
            driver = new InternetExplorerDriver();

        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();

        }
        driver.get("https://"+ environment + address);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
