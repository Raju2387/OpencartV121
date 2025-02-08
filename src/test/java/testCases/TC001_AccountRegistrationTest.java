package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	void verify_account_registration() throws InterruptedException  {
		
		HomePage hp = new HomePage(driver);
		Thread.sleep(200);
		hp.clickMyAccount();
		
		hp.clickRegister();

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

		regpage.setFirstName(randomstring().toUpperCase());
		regpage.setLastName(randomstring().toUpperCase());
		
		regpage.setphonenumber(gennumber());

		regpage.setEmail(genrated() + "@gmail.com");

		String password = genrated();

		regpage.setPassword(password);

		regpage.setconformPassword(password);

		regpage.clickNewsletter();

		regpage.acceptPrivacyPolicy();

		regpage.clickContinue();

		String conf = regpage.getConfirmationMessage();
		Assert.assertEquals(conf, "Your Account Has Been Created!");

	}

}
