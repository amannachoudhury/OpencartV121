package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pajeObjects.HomePage;
import pajeObjects.LoginPage;
import pajeObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("*** Starting TC002_LoginTest***");
		
		try
		{
		HomePage hp=new HomePage(driver);
		
		hp.clickMyaccount();
		
		hp.clickLogin();
		logger.info("Clicked on login");
		
		LoginPage lp=new LoginPage(driver);
		
		logger.info("Providing data from properties file");
		
		lp.setEmail(p.getProperty("email"));  //reading email from properties file
		lp.setPassword(p.getProperty("password"));   	//reading password from properties file
		
		lp.clickLogin();
		logger.info("clicked on LoginBtn");
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true,"Login failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		
		logger.info("***Finished TC002_LoginTest***");
	}

}
