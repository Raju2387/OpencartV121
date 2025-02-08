package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") 
	 WebElement firstName;
	
	@FindBy(xpath="//input[@id='input-lastname']") 
	 WebElement lastName;
	
	@FindBy(xpath="//input[@name='email']") 
	 WebElement eMail;
	
	@FindBy(xpath ="//input[@id='input-telephone']")
	WebElement phonenumber;
	
	@FindBy(xpath="//input[@name='password']") 
	 WebElement password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confompassword;
	
	
	@FindBy(xpath="//label[normalize-space()='No']") 
	 WebElement newsletter;
	
	@FindBy(xpath="//input[@name='agree']")
	 WebElement agree;

	@FindBy(xpath="//input[@value='Continue']") 
	 WebElement continueBtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") 
	 WebElement yourAccountHasBeenCreated;

	public void setFirstName(String fname) {
		firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		lastName.sendKeys(lname);
	}
	
	public void setphonenumber(String number) {
		phonenumber.sendKeys(number);
	}
	public void setEmail(String email) {
		eMail.sendKeys(email);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void setconformPassword(String pwdc) {
		confompassword.sendKeys(pwdc);
	}
	
	public void clickNewsletter() {
		newsletter.click();
	}

	public void acceptPrivacyPolicy() {
		agree.click();
	}
	
	public void clickContinue() {
		continueBtn.click();
	}
	
	public String getConfirmationMessage() 
	{
		try {
			return(yourAccountHasBeenCreated.getText());
	}catch(Exception e) {
		return(e.getMessage());
	}
		
	}

}
