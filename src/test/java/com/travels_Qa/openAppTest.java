package com.travels_Qa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.FindAgentPage;
import pages.HomePage;
import pages.StatesPage;

public class openAppTest {

	
		
	final String file = "C:\\Users\\mohamed\\Desktop\\travelesIns_QA\\travelsIns-QA\\src\\test\\resources\\config.properties";

	WebDriver driver = null;
	Properties prop;
	HomePage HomePage;
	FindAgentPage findAgentPage;
	StatesPage statesPage;
	  
	  @BeforeTest
	  public void setUp() throws IOException {
	    
	    prop = new Properties();
	    FileInputStream ip = new FileInputStream(file);
	    prop.load(ip);
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.get(prop.getProperty("url"));
	  
	  }
	  
	  @AfterTest
	  public void closeBrowser(){
		  driver.quit();
	  }
	  
	@Test
	public void locateAgent() {
		HomePage = new HomePage(driver);
		findAgentPage = new FindAgentPage(driver);
		statesPage = new StatesPage(driver);

		Assert.assertEquals(driver.getTitle(),
				"Travelers Insurance | Auto Insurance | Car Insurance Quotes | Business | Homeowners",
				"title not showing");
		Assert.assertTrue(HomePage.findAgentButton.isDisplayed(), "Locate an agent in your area");

		HomePage.findAgentButton.click();

		Set<String> winIds = driver.getWindowHandles();

		Iterator<String> it = winIds.iterator();

		String mainWindow = it.next();
		String firstWindow = it.next();

		driver.switchTo().window(firstWindow);
		Assert.assertEquals(driver.getTitle(),
				"Find A Local Travelers Insurance Agent in your Area | Travelers Insurance",
				" second page title not showing");

		Assert.assertTrue(findAgentPage.findInssuranceButton.isDisplayed(), "Find An Insurance Agent By State");
		
		findAgentPage.findInssuranceButton.click();
		
		Assert.assertEquals(driver.getTitle(), "Travelers Insurance Agents in your State | Travelers Insurance",
				"page title not showing");

		Assert.assertTrue(statesPage.statesHeaderText.isDisplayed(),
				"Find an Insurance Agent by State - Select Your State");
		driver.switchTo().window(mainWindow);

	}

}
