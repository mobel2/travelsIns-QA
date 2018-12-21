package com.travels_Qa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.FindAgentPage;
import pages.HomePage;
import pages.StatesPage;

public class agenciesDataTest {

	final String file = "C:\\Users\\mohamed\\Desktop\\travelesIns_QA\\travelsIns-QA\\src\\test\\resources\\config.properties";
	String excelPath = "C:\\Users\\mohamed\\Desktop\\agencies.xlsx";
	Xls_Reader data = new Xls_Reader(excelPath);

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
	public void closeBrowser() {
		driver.quit();
	}

	@Test
	public void agenciesData() {
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

		statesPage.Alabama.click();
		statesPage.Alexandria.click();

		Select s = new Select(statesPage.Select);
		s.selectByIndex(4);
		
		List<WebElement> alexandiaData=statesPage.alexData;
		
		List<WebElement> aganecyName=statesPage.alexInfo;
		List<WebElement> aganecyAdres=statesPage.alexInfo1;
		List<WebElement> aganecyPhone=statesPage.alexInfo2;
		List<WebElement> aganecDist=statesPage.alexInfo3;

		
		for (int i = 0; i < alexandiaData.size(); i++) {
			data.setCellData( "data","Agency", i+2, (aganecyName.get(i).getText()));
			data.setCellData("data", "Address", i+2, (aganecyAdres.get(i).getText()));
			data.setCellData("data", "Phone", i+2, (aganecyPhone.get(i).getText()));
			data.setCellData("data", "Distance", i+2, (aganecDist.get(i).getText()));
		}

		driver.switchTo().window(mainWindow);

	}

}
