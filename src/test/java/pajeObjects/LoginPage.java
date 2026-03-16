package pajeObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(how=How.XPATH,using="//input[@id='input-email']")
	WebElement txtEmailAddress;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(how=How.XPATH,using="//input[@class='btn btn-primary']")
	WebElement btnLogin;

	public void setEmail(String email)
	{
		txtEmailAddress.sendKeys(email);
	}

	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin()
	{
		btnLogin.click();
	}

	








}
