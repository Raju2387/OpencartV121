package testCases;
import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Ecommerce {
	WebDriver driver;
	WebDriverWait wait;
	
  @Test(priority=1)
  public void f() {
	  driver.findElement(By.xpath("//li[@class='menu-item']//a[normalize-space()='Register']")).click();
	  driver.findElement(By.xpath("//input[@name='name']")).sendKeys(randomstring());
	  driver.findElement(By.xpath("//input[@name='email']")).sendKeys(genrated());
	  driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(gennumber());
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Raju@2001");
	  driver.findElement(By.xpath("//input[@name='password_confirmation']")).sendKeys("Raju@2001");
	  driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();
	  
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='menu-item']//a[normalize-space()='My Account']")));
	String text= driver.findElement(By.xpath("//li[@class='menu-item']//a[normalize-space()='My Account']")).getText();
	System.out.println(text);
	if(text.contains("My Account")) {
		System.out.println("login page sucesfull");
	}else {
		System.out.println("login page failde");
	}
  }
  @Test(priority=2)
 public void selectProduct () 
 {
		driver.findElement(By.xpath("//img[@alt='Fruit Seeds']")).click();
		
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[normalize-space()='Fruit Seeds']")));
	
	
	String text1=driver.findElement(By.xpath("//h1[normalize-space()='Fruit Seeds']")).getText();
	
	  System.out.println(text1);
	  if(text1.contains("Fruit Seeds")) {
		  System.out.println("Fruit Seeds is displayed ");
	  }else {
		  System.out.println("Fruit Seeds not displayed ");
	  }
	  
	  
	driver.findElement(By.xpath("//img[@title='Namdhari NS-295 Watermelon Seeds']")).click();
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[normalize-space()='Namdhari NS-295 Watermelon Seeds']")));
	  
	String text2=driver.findElement(By.xpath("//h1[normalize-space()='Namdhari NS-295 Watermelon Seeds']")).getText();
	
	  System.out.println(text2);
	  if(text1.contains("Namdhari NS-295 Watermelon Seeds")) {
		  System.out.println("Namdhari NS-295 Watermelon Seeds is displayed ");
	  }else {
		  System.out.println("Namdhari NS-295 Watermelon Seeds not displayed ");
	  }
	  
	  
	  
	  
	  driver.findElement(By.xpath("//button[normalize-space()='add to cart']")).click();
	  
	  
	  
	String text3=  driver.findElement(By.xpath("//span[@class='sub-total']")).getText();
	System.out.println(text3);
	if(text3.contains("₹01.00")) {
		  System.out.println("My Cart is added :01 ");
	  }else {
		  System.out.println("not added any product");
	  }
	  
	  
}
  @BeforeMethod
  public void beforeMethod() {
	  driver=new ChromeDriver();
	  driver.get("https://www.navadhanyaagroseeds.com/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  wait=new WebDriverWait(driver, Duration.ofSeconds(30));
  }

  @AfterMethod
  public void afterMethod() {
	  
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
	
		return (genratedstrings+"@"+genratednumbers );
		
	}

  
  
  
  
}
