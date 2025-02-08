package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		
	}


	
	@FindBy(xpath ="//input[@id='input-email']")
	WebElement textemailadress;
	
	@FindBy(xpath ="//input[@id='input-password']")
	WebElement textpasswordadress;
	
	
	@FindBy(xpath ="//input[@value='Login']")
	WebElement loginbutton;
	

	
	
	public void setemaillog(String email) {
		textemailadress.sendKeys(email);
	}

	public void setpasswordlog(String pwd) {
		textpasswordadress.sendKeys(pwd);
	}

	public void clicklogin() {
		loginbutton.click();
	}
	
}
