package bddTest;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserStory03StepDefinitions {
	WebDriver driver=null;
	
	@Given("^System setup and Admin loggin the system$")
	
	public void adminloginthesystem() throws Throwable {
			driver=new FirefoxDriver();
		    driver.navigate().to("http://localhost:8080/WebApp4");
		    driver.findElement(By.id("loginForm:stuNo")).sendKeys("admin");
		    driver.findElement(By.id("loginForm:password")).sendKeys("admin");
		    driver.findElement(By.id("loginForm:confirmPassword")).click();
	
	}
	@Given("^Admin click the \"([^\"]*)\" button$")
	public void adminclickbutton(String arg1) throws Throwable {
		String buttonid="j_idt21:"+arg1; 
		driver.findElement(By.id(buttonid)).click();
	}
	
	@When("^Admin enters termname \"([^\"]*)\" startdate \"([^\"]*)\" enddate \"([^\"]*)\"$")
	public void firstinformationententered(String arg1, String arg2, String arg3) throws Throwable {
		   driver.findElement(By.id("maintainTermForm:inname")).sendKeys(arg1);
		   driver.findElement(By.id("maintainTermForm:instartDate")).sendKeys(arg2);
		   driver.findElement(By.id("maintainTermForm:inendDate")).sendKeys(arg3); 
	}
	@When("^Admin click create button$")
	public void Adminclickcreatebutton() throws Throwable {
		 driver.findElement(By.id("maintainTermForm:createTerm")).click();
		
	}
	
	
	@Then("^The message shows creating Term \"([^\"]*)\" successfully$")
	public void TermCreated(String arg1) throws Throwable {
	String message= driver.findElement(By.id("maintainTermForm:successMessagePane")).getText();
	Assert.assertEquals("Create Term ["+arg1+"]: SUCCESS",message);
	}
	
	@Given("^Admin login and create a term with termname \"([^\"]*)\", startDate=\"([^\"]*)\", endDate=\"([^\"]*)\"$")
	public void Admin_login_and_create_a_term_with_termname_startDate_endDate(String arg1, String arg2, String arg3) throws Throwable{
		driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	    driver.findElement(By.id("loginForm:stuNo")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:password")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:confirmPassword")).click();
	    driver.findElement(By.id("j_idt21:maintainTerm")).click();
	    driver.findElement(By.id("maintainTermForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermForm:instartDate")).sendKeys(arg2);
		driver.findElement(By.id("maintainTermForm:inendDate")).sendKeys(arg3);
		driver.findElement(By.id("maintainTermForm:createTerm")).click();
	}
	
	
	
	@And("^The message shows create term \"([^\"]*)\" successful$")
	public void SuccessfullyCreate(String arg1) throws Throwable {
		String message1=driver.findElement(By.id("maintainTermForm:successMessagePane")).getText();
		Assert.assertEquals("Create Term ["+arg1+"]: SUCCESS", message1);
	}
		
	@Then("^The message shows create Term \"([^\"]*)\" failed$")
	public void TermOverlapping(String arg1) throws Throwable {
	  String message2=driver.findElement(By.id("maintainTermForm:errormessagePane")).getText();
	  Assert.assertEquals("Create Term ["+arg1+"]: FAILED", message2);
	}


}
