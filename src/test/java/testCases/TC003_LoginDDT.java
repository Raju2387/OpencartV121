package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="DataDriven")
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {
		try {

			// homepage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			Thread.sleep(200);
			hp.clicklogin();

			// loginpage

			LoginPage lp = new LoginPage(driver);

			lp.setemaillog(email);
			lp.setpasswordlog(pwd);

			lp.clicklogin();

			// myaccountpage
			myAccountPage myacc = new myAccountPage(driver);
			boolean targetpage = myacc.ismyaccoutpage();

			if (exp.equalsIgnoreCase("valid")) {
				if (targetpage == true) {
					Assert.assertTrue(true);
					myacc.clicklogout();
				}

				else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("invalid")) {
				if (targetpage == true) {
					myacc.clicklogout();
					Assert.assertTrue(false);

				} else {
					Assert.assertTrue(true);
				}

			}

		} catch (Exception e) {
			Assert.fail();
		}

	}
}
