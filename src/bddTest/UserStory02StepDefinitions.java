package bddTest;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserStory02StepDefinitions {
	
	WebDriver driver=null;
	
	@Given("^student A on open the system website$")
	public void student_A_on_open_the_system_website() throws Throwable {
		driver=new FirefoxDriver();
		driver.navigate().to("http://localhost:8080/WebApp4");
	}

	@When("^Student A enters student number \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void Student_A_enters_student_number_and_password(String arg1, String arg2) throws Throwable {
		driver.findElement(By.id("loginForm:stuNo")).sendKeys(arg1);
		driver.findElement(By.id("loginForm:password")).sendKeys(arg2);
	}

	@When("^Student A click on the button$")
	public void Student_A_click_on_the_button() throws Throwable {
		driver.findElement(By.id("loginForm:confirmPassword")).click();
	}

	@Then("^There student A will be on the \"([^\"]*)\" page$")
	public void There_student_A_will_be_on_the_page(String arg1) throws Throwable {
	    Assert.assertEquals("Welcome Dog", driver.getTitle());
	}

	@Given("^login in with correct student number \"([^\"]*)\" and \"([^\"]*)\"$")
	public void login_in_with_correct_student_number_and(String arg1, String arg2) throws Throwable {
		driver=new FirefoxDriver();
		driver.navigate().to("http://localhost:8080/WebApp4");
		driver.findElement(By.id("loginForm:stuNo")).sendKeys(arg1);
		driver.findElement(By.id("loginForm:password")).sendKeys(arg2);
	}

	@When("^click the register button$")
	public void click_the_register_button() throws Throwable {
		driver.findElement(By.id("loginForm:confirmPassword")).click();
	}

	@Then("^fail to login the system$")
	public void fail_to_login_the_system() throws Throwable {
		String actual=driver.findElement(By.id("loginForm:messagebox")).getText();
		Assert.assertEquals("Invalid Password. Please try again.", actual);
	}

}
