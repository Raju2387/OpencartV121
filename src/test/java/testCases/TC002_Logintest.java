package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;

public class TC002_Logintest extends BaseClass {

	
	
	@Test(groups= {"Sanity","Master"})
	public void verifylogin() throws InterruptedException {
		
		try {
		
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		Thread.sleep(200);
		hp.clicklogin();
		
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setemaillog(prop.getProperty("email"));
		lp.setpasswordlog(prop.getProperty("password"));
		
		
		lp.clicklogin();
		
		
		myAccountPage myacc= new myAccountPage(driver);
		boolean targetpage=myacc.ismyaccoutpage();
		
		Assert.assertTrue(targetpage);  
		//Assert.assertEquals(targetpage, true,"login is faild");
		}
		catch(Exception e) {
			Assert.fail();
		}
	}
}
