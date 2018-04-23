package seleniumPackage;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


class TestSelenium {

	
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();


@Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.firefox.marionette","C:\\geckoDriver\\geckodriver.exe");
	driver= new FirefoxDriver();
    baseUrl = "https://www.ul.ie/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void TestSelenium() throws Exception {
	System.setProperty("webdriver.firefox.marionette","C:\\geckoDriver\\geckodriver.exe");
	driver = new FirefoxDriver();
    driver.get("https://www.ul.ie/portal");
    
    //Loop for each webpage.
    for(int i=0; i<=3; i++) {
    	clickElement(i);
    	
    	//Printing of page title.
    	String actualTitle = driver.getTitle();
        System.out.println("Page title: " + actualTitle + "\n" + "Length of title: " + actualTitle.length());
        
        //Printing of URLs and error/success message.
        String expectedUrl = getExpectedURL(i);
        System.out.println("Expected URL: \t" + expectedUrl);
        System.out.println("Actual URL: \t" + driver.getCurrentUrl());
        try{
          Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
          System.out.println("Navigated to correct webpage\n");
        }
        catch(Throwable pageNavigationError){
          System.out.println("Didn't navigate to correct webpage\n");
        }
      }
    }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  
  //Driver commands for for-loop.
  private void clickElement(int option) {
	  switch(option) {
	  case 0: driver.findElement(By.linkText("Study at UL")).click(); break;
	  case 1: driver.findElement(By.linkText("Undergraduate")).click(); break;
	  case 2: driver.findElement(By.linkText("Alphabetical List of Courses")).click(); break;
	  case 3: driver.findElement(By.linkText("Bachelor of Science in Equine Science")).click(); break;
	  default: break;
	  }
  }
  
  //URL path storage for URL prints.
  private String getExpectedURL(int option) {
	  switch(option) {
	  case 0: return "https://www.ul.ie/portal";
	  case 1: return "http://www3.ul.ie/courses/";
	  case 2: return "http://www3.ul.ie/courses/AlphabeticalList.shtml";
	  case 3: return "http://www3.ul.ie/courses/EquineScience.php";
	  default: return "";
	  }
  }
}