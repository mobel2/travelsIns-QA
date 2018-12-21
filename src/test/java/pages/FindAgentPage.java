package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindAgentPage {
	
	
	public FindAgentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
			
		}
	
	@FindBy(css=".find-by-state a")
	public WebElement findInssuranceButton;

}
