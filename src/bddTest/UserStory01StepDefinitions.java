package bddTest;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserStory01StepDefinitions {
	WebDriver driver=null;
	
	@Given("^student A open the register page$")
	public void openRegisterpage() throws Throwable {
	    driver=new FirefoxDriver();
	    driver.navigate().to("http://localhost:8080/WebApp4");
	    driver.findElement(By.id("loginForm:register")).click();
	}
	@When("^Student A enters his firstname \"([^\"]*)\" last name \"([^\"]*)\" Birthdate\"([^\"]*)\"$")
	public void Student_A_enters_his_firstname_last_name_Birthdate(String arg1, String arg2, String arg3) throws Throwable {
		 driver.findElement(By.id("loginForm:firstname")).sendKeys(arg1);	
		 driver.findElement(By.id("loginForm:lastname")).sendKeys(arg2);	
		 driver.findElement(By.id("loginForm:birthdate")).sendKeys(arg3);	
		 driver.findElement(By.id("loginForm:school")).sendKeys("COMP");
	}
	@When("^Student A click on the submit button$")
		public void student_A_click_on_the_submit_button() throws Throwable {
		driver.findElement(By.id("loginForm:confirmregister")).click();
	}
	@Then("^The student sees the messagebox contain a student number and password$")
	public void the_student_see_the_messagebox_contain_a_student_number_and_password() throws Throwable {
		
		String actual=driver.findElement(By.id("loginForm:feedbackMessagePane")).getAttribute("class");
		Assert.assertEquals("successMessagePane", actual);
	
	
	}

}				
