package task;

import javafx.scene.control.CheckBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by SANYA on 5/20/2017.
 */
@Listeners(listener.Listener.class)
public class TestCases extends BaseTest {


    @Test
    public void searchAutomation()
    {   // open google.com
        HomePage
        homepage = new HomePage(driver);
        driver.get("http://www.google.com");
        // search for "automation" request
        homepage.doSearchAutomation("automation");
        // open first link and verify if Title as EXPECTED
        assert(homepage.openLinkAssert("automation"));
    }

   @Test
    public void searchDomain()
   {   // open google.com
       HomePage
       homepage = new HomePage(driver);
       // search for "automation" request
       homepage.doSearchAutomation("automation");
       // verify if "testautomationday.com" present ir result on pages from 1 to 5
       assert(homepage.expectDomain("testautomationday.com"));
    }

    @Test
    public void fillRegForm() throws InterruptedException
    {
        HomePage
        homepage = new HomePage(driver);
        homepage.fillRegForm();
    }
    @Test
    public void textVerification()
    { //  driver.get("the-internet.herokuapp.com");
        driver.findElement(By.linkText("A/B Testing")).click();

        String expectedTitle = "A/B Test Control";
        WebElement actualTitle = driver.findElement(By.xpath("//*[@id='content']/div/h3"));
        String actualText = actualTitle.getText();
        assertEquals(expectedTitle, actualText);
        }
    @Test
    public void textCheckBoxes()throws InterruptedException
    { //  driver.get("the-internet.herokuapp.com");
        driver.findElement(By.linkText("Checkboxes")).click();
        Thread.sleep(4000);
        //Test for first checkBox
        WebElement chB1 = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]"));
        boolean status= chB1.isDisplayed();
        System.out.println("First checkBox is Displayed >>"+status);
        boolean enabled_status=chB1.isEnabled();
        System.out.println("First checkBox is Enabled >>"+enabled_status);
        boolean selected_status=chB1.isSelected();
        System.out.println("First checkBox is Selected >>"+selected_status);
        chB1.click();
        boolean selected_status_new=chB1.isSelected();
        System.out.println("First checkBox is Selected >>"+selected_status_new);
        //Test for Second checkBox

        /*        String expectedTitle = "A/B Test Control";
        WebElement actualTitle = driver.findElement(By.xpath("/*//*[@id='content']/div/h3"));
        String actualText = actualTitle.getText();
        assertEquals(expectedTitle, actualText);*/
    }

    @Test
    public void testx()throws InterruptedException
      {
          HomePage
          homepage = new HomePage(driver);
          driver.findElement(By.linkText("Checkboxes")).click();
          homepage.checkBoxDefSelFalse("//*[@id='checkboxes']/input[1]");
          homepage.checkBoxDefSelTrue("//*[@id='checkboxes']/input[2]");

      }

}



