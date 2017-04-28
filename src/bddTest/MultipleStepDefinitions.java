package bddTest;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MultipleStepDefinitions {
	
	WebDriver driver=null;
	
	@Given("^initialize a system, login as admin$")
	public void initializeLoginAsAdmin() throws Throwable {
		
		driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	    driver.findElement(By.id("loginForm:stuNo")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:password")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:confirmPassword")).click();
	}

	@Given("^goto \"([^\"]*)\" page$")
	public void gotoPage(String arg1) throws Throwable {
		String fullId="j_idt21:"+arg1;
	    driver.findElement(By.id(fullId)).click();
	}
	
	@Given("^initialize a system, login as admin, create the term with name=\"([^\"]*)\", startDate=\"([^\"]*)\", endDate=\"([^\"]*)\"$")
	public void initialize_a_system_login_as_admin_create_the_term_with_name_startDate_endDate(String arg1, String arg2, String arg3) throws Throwable {
		driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	    driver.findElement(By.id("loginForm:stuNo")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:password")).sendKeys("admin");
	    driver.findElement(By.id("loginForm:confirmPassword")).click();
	    String fullComponentId="j_idt21:maintainTerms";
	    driver.findElement(By.id(fullComponentId)).click();
	    driver.findElement(By.id("maintainTermsForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermsForm:start-Date")).sendKeys(arg2);
		driver.findElement(By.id("maintainTermsForm:end-Date")).sendKeys(arg3);
		driver.findElement(By.id("maintainTermsForm:createTerm")).click();
	}

	@When("^create the term with name=\"([^\"]*)\", startDate=\"([^\"]*)\", endDate=\"([^\"]*)\"$")
	public void createTermWithNameStartDateEndDate(String arg1, String arg2, String arg3) throws Throwable {
		driver.findElement(By.id("maintainTermsForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermsForm:start-Date")).sendKeys(arg2);
		driver.findElement(By.id("maintainTermsForm:end-Date")).sendKeys(arg3);
		driver.findElement(By.id("maintainTermsForm:createTerm")).click();
	}
	@Given("^set term \"([^\"]*)\" enrolldate to\"([^\"]*)\"$")
	public void set_term_enrolldate_to(String arg1, String arg2) throws Throwable {
		driver.findElement(By.id("j_idt21:maintainTerms")).click();
		driver.findElement(By.id("maintainTermsForm:inname")).sendKeys(arg1);
		driver.findElement(By.id("maintainTermsForm:enroll-start")).sendKeys(arg2);
	}

	@When("^click update button$")
	public void click_update_button() throws Throwable {
	   driver.findElement(By.id("maintainTermForm:UpdateTerm")).click();
	}
	
	@Then("^update failed \"([^\"]*)\"$")
	public void update_failed_invalid_date(String arg1) throws Throwable {
	    Assert.assertEquals("invalid date", arg1);
	    
	}	
	
	@Given("^show create Term \"([^\"]*)\" success$")
	public void showCreateSuccessAtGiven(String arg1) throws Throwable {
		String msg1=driver.findElement(By.id("maintainTermsForm:successMessagePane")).getText();
		Assert.assertEquals("Create Term ["+arg1+"]: SUCCESS", msg1);
	}

	@Then("^show create \"([^\"]*)\" \"([^\"]*)\" success$")
	public void showCreateSuccessAtThen(String arg1, String arg2) throws Throwable {
		String msg2=driver.findElement(By.id("maintainTermsForm:successMessagePane")).getText();
		Assert.assertTrue("creation failed", msg2.equals("Create "+arg1+" ["+arg2+"]: SUCCESS"));
		//Create Term [summ]: SUCCESS
	}
		
	@Then("^show create \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" fail$")
	public void showCreateOverlappingFail(String arg1, String arg2, String arg3) throws Throwable {
		String msg=driver.findElement(By.id("maintainTermsForm:errorMessagePane")).getText();
		Assert.assertTrue("creation success", msg.equals("Create "+arg1+" ["+arg2+"] "+arg3+": FAILED"));
	}

}
