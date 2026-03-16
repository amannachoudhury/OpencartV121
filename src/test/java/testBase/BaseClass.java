package testBase;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;  //log file
	public Properties p;

	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})	
	public void setup(String os,String br) throws Exception
	{

		//loading config.properties file
		//FileInputStream file=new FileInputStream("./src//test//resources//config.properties");
		FileReader file=new FileReader("./src//test//resources//config.properties");  //alternative of Fileinputstream
		p=new Properties();
		p.load(file);


		logger=LogManager.getLogger(this.getClass());

		switch(br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default:System.out.println("invalid browser name...");return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appUrl")); //reading Url from properties file
	}

	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}


	public String randomeString()
	{
		String generatedstring=RandomStringUtils.secure().nextAlphabetic(5);
		return generatedstring;
	}

	public String randomeNumber()
	{
		String generatednumber=RandomStringUtils.secure().nextNumeric(10);
		return generatednumber;
	}


	public String randomeAlphaNumeric()
	{
		String generatedstring=RandomStringUtils.secure().nextAlphabetic(5);
		String generatednumber=RandomStringUtils.secure().nextNumeric(5);
		return (generatedstring+"@"+generatednumber);
	}

	public String captureScreen(String tname) {

		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" +tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}
	
	 public static void sendReport(String reportPath) {   

	        try {

	            EmailAttachment attachment = new EmailAttachment();
	            attachment.setPath(reportPath);
	            attachment.setDisposition(EmailAttachment.ATTACHMENT);
	            attachment.setDescription("Extent Report");
	            attachment.setName("Automation Report");

	            MultiPartEmail email = new MultiPartEmail();

	            email.setHostName("smtp.gmail.com");
	            email.setSmtpPort(465);

	            email.setAuthenticator(
	                    new DefaultAuthenticator("amannachoudhury@gmail.com", "app_password"));

	            email.setSSLOnConnect(true);

	            email.setFrom("your_email@gmail.com");
	            email.setSubject("Automation Test Report");
	            email.setMsg("Hi Team,\n\nPlease find the attached automation test report.");

	            email.addTo("team@gmail.com");

	            email.attach(attachment);

	            email.send();

	            System.out.println("Email sent successfully");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
