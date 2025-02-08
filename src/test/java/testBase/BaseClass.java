package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException, InterruptedException {
		
		FileReader file= new FileReader("./src//test//resources//config.properties");
		prop= new Properties();
		prop.load(file);
		
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities cap= new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) 
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
				
			}
			else
			{
				System.out.println("not maching windows os");
				return;
			}
			
			//browser
			
			switch(br.toLowerCase()) 
			{
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox": cap.setBrowserName("firefox"); break;
			default : System.out.println("no macthing browser"); return;
			
			}	
			
			Thread.sleep(200);
			driver= new RemoteWebDriver(new URL("http://192.168.1.6:4444"),cap);
		}
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br .toLowerCase()) 
			{
			case "chrome": driver= new ChromeDriver(); break;
			case "edge": driver= new EdgeDriver(); break;
			case "firefox": driver= new FirefoxDriver(); break;
			default : System.out.println("invalid browser ...,"); return;
			
			}
			
		}
		

	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		driver.get(prop.getProperty("appurl"));
		driver.manage().window().maximize();
		
		
	}
	
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void teardown() 
	{
		driver.quit();
	}
	
	public String randomstring()
	{
		String genratedstrings=RandomStringUtils.randomAlphanumeric(6);
		return genratedstrings;
	}
	
	public String gennumber()
	{
		String genratednumbers=RandomStringUtils.randomNumeric(10);	
		return genratednumbers;
		
	}
	
	public String genrated() 
	{
		String genratedstrings=RandomStringUtils.randomAlphanumeric(3);
		

		String genratednumbers=RandomStringUtils.randomNumeric(3);	
	
		return (genratedstrings+genratednumbers );
		
	}


	public String captureScreen(String tname) throws IOException
	
	{
		String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takescrenshot=(TakesScreenshot) driver;
		File sourceFile =takescrenshot.getScreenshotAs(OutputType.FILE);
		
		
		String targetFilePath=System.getProperty("D:\\Raju\\OpencartV121\\screenshorts") + tname + "_" + timeStamp + ".png";
		File targetFile= new File(targetFilePath);
		
		
		sourceFile.renameTo(targetFile);
		
		
		return targetFilePath;
	}

}
