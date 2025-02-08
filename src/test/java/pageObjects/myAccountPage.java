package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccountPage extends BasePage{

	public myAccountPage(WebDriver driver) 
	{
		super(driver);
		
	}


	@FindBy(xpath ="//h2[normalize-space()='My Account']")
	WebElement myaccountisdisplayed;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout;
	

	
	public boolean ismyaccoutpage() 
	{
		
		try {
		 return (myaccountisdisplayed.isDisplayed());
		}
		catch(Exception e) {
			return false;
			
		}
	}
	
	public void clicklogout() {
		logout.click();
	}
}
