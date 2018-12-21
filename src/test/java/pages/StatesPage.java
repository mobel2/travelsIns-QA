package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StatesPage {
	
	public StatesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
			
		}
	@FindBy(tagName="h2")
	public WebElement statesHeaderText;

	@FindBy(css=".stateContainer ul a")
	public List<WebElement> statesButton;
	
	@FindBy(css=".city_letter_block ul a")
	public List<WebElement> citiesList;
	
	@FindBy(id="page_results")
	public WebElement Select;
	
	@FindBy(xpath="//*[text()=\"Alabama\"]")
	public WebElement Alabama;
	
	@FindBy(xpath="//*[text()=\"ALEXANDRIA\"]")
	public WebElement Alexandria;
	
	@FindBy(xpath="//*[@id=\"agent_table\"]/div/div[5] ")
	public List<WebElement> alexData;
	
	@FindBy(xpath="//*[@id=\"agent_table\"]/div/div[5]/span[1]")
	public List<WebElement> alexInfo;
	@FindBy(xpath="//*[@id=\"agent_table\"]/div/div[5]/span[2]")
	public List<WebElement> alexInfo1;
	@FindBy(xpath="//*[@id=\"agent_table\"]/div/div[5]/span[3]")
	public List<WebElement> alexInfo2;
	@FindBy(xpath="//*[@id=\"agent_table\"]/div/div[5]/span[4]")
	public List<WebElement> alexInfo3;
	
}
