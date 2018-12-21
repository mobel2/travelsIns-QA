package com.travels_Qa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import pages.FindAgentPage;
import pages.HomePage;
import pages.StatesPage;

public class statesLocations {
	
	
	final String file ="C:\\Users\\mohamed\\Desktop\\travelesIns_QA\\travelsIns-QA\\src\\test\\resources\\config.properties";
	String excelPath="C:\\Users\\mohamed\\Desktop\\cities.xlsx";
	Xls_Reader data = new Xls_Reader(excelPath);
	
	WebDriver driver=null;
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
	  public void agenciesStates() {
			HomePage = new HomePage(driver);
			findAgentPage = new FindAgentPage(driver);
			statesPage = new StatesPage(driver);
			
			HomePage.findAgentButton.click();

			Set<String> winIds = driver.getWindowHandles();

			Iterator<String> it = winIds.iterator();

			String mainWindow = it.next();
			String firstWindow = it.next();

			driver.switchTo().window(firstWindow);
			findAgentPage.findInssuranceButton.click();
			
			
		
			List<WebElement> states= statesPage.statesButton;
		    System.out.println(states.size());
		    
			List<WebElement> cities= statesPage.citiesList;
		
		for (int j = 0; j < cities.size(); j++) {

			states.get(j).click();

			data.setCellData("data", "Cities", j + 2, (cities.get(j).getText()));

			driver.navigate().back();

		}

		
		
		
		driver.switchTo().window(mainWindow);

	}

}
