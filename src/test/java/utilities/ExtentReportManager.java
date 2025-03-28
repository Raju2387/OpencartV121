package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements  ITestNGListener
{
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
    
    String repName;
    
    
    public void onStart(ITestContext textcontext) 
    {
    	String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	
    	repName ="Test-Reports-" + timeStamp + ".html";
    	sparkReporter= new ExtentSparkReporter("D:\\Raju\\OpencartV121\\reports"+ repName);
    	
    	
    	sparkReporter.config().setDocumentTitle("opencart Automation Report");
    	sparkReporter.config().setReportName("opencart Functional Testing");
    	sparkReporter.config().setTheme(Theme.DARK);
    	
    	extent= new ExtentReports();
    	extent.attachReporter(sparkReporter);
    	extent.setSystemInfo("Application", "opencart");
    	extent.setSystemInfo("Module", "Admin");
    	extent.setSystemInfo("Sub Module", "Customers");
    	extent.setSystemInfo("User Name",System.getProperty("user.name") );
    	extent.setSystemInfo("Enviornemnt", "QA");
    	
    String os = textcontext.getCurrentXmlTest().getParameter("os");
    extent.setSystemInfo("Operating System", os);
    
  String browser=textcontext.getCurrentXmlTest().getParameter("browser");
  extent.setSystemInfo("Browser", browser);
  
  
  List<String> includedGroups = textcontext.getCurrentXmlTest().getIncludedGroups();
  
  if(!includedGroups.isEmpty()) {
	  extent.setSystemInfo("Groups", includedGroups.toString());
	  
        }
    	
    }
	
    
    public void onTestSuccess(ITestResult result)
    
    {
    	
    	test= extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.PASS, result.getName()+" got successfully executed ");
    	
    	
    }
	
    public void onTestFailure(ITestResult result) {
    	
    	test=extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	
    	test.log(Status.FAIL, result.getName()+" got failed");
    	test.log(Status.INFO, result.getThrowable().getMessage());
    	
    	
    	try {
    		
    		String imgPath= new BaseClass().captureScreen(result.getName());
    		test.addScreenCaptureFromPath(imgPath);
    		
    	}catch(IOException e1) {
    		
    		e1.printStackTrace();
    	}
    	
    }
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		
		
		String pathofextentreports=System.getProperty("D:\\Raju\\OpencartV121\\reports")+ repName;
		File extentReport= new File(pathofextentreports);
		
		try {
			
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		
		 try {
		
			URL url = new URL("file:///"+System.getProperty("user.dir")+ "\\reports\\" +repName);
			
			// create the email
			
			 ImageHtmlEmail email = new ImageHtmlEmail();
			 email.setDataSourceResolver(new DataSourceUrlResolver(url));
			 email.setHostName("smtp.googlemail.com");
			 email.setSmtpPort(465);
			 email.setAuthenticator(new DefaultAuthenticator("rajunaik4960@gmail.com", "Raju@2001"));
			 email.setSSLOnConnect(true);
			 email.setFrom("rajunaik4960@gmail.com");
			 email.setSubject("Test Result");
			 email.setMsg("Please find attached report...,");
			 email.addTo("rajunaik3960@gmail.com");
			 email.attach(url,"extent report","please check reports");
			 email.send();
		 }
		catch(Exception e)
		 {
			e.printStackTrace();
			
		}
		
	
   }
}
