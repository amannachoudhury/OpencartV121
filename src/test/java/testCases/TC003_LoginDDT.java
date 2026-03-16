package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pajeObjects.HomePage;
import pajeObjects.LoginPage;
import pajeObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid - login success - test pass - logout
  Data is valid - login failed - test failed

Data is invalid - login success - test failed - logout
 Data is invalid - login failed - test pass
 */

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven") //DataProviders is a class and it is not in the same class it is in utilities package
	public void verify_loginDDT(String email,String pwd,String result) throws Exception
	{

		logger.info("***Starting TC003_LoginDDT***");

		try
		{
			//HomePage
			HomePage hp=new HomePage(driver);
			hp.clickMyaccount();
			hp.clickLogin();

			//LoginPage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);  
			lp.setPassword(pwd);   	
			lp.clickLogin();

			//MyAccountPage
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExists();


			/*Data is valid - login success - test pass - logout
	  Data is valid - login failed - test failed
			 */


			if(result.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();

					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}

			/*Data is invalid - login success - test failed - logout
	  Data is invalid - login failed - test pass
			 */

			if(result.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();

					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}


		}

		catch(Exception e)
		{
			Assert.fail();
		}


		logger.info("***Finished TC003_LoginDDT***");

	}

}


