package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import constantes.URLs;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.JobsPage;
import util.Util;

public class JobTest{

	HomePage homePage;
	JobsPage jobsPage;
	WebDriver driver;
	
	public JobTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
		jobsPage = new JobsPage(driver);
	}
	
	@Given("^I am on the job's page$")
	public void i_am_on_the_job_s_page() throws Throwable {
		driver.get(URLs.JOB_URL);
	}

	@When("^I Fill the information's fields$")
	public void i_Fill_the_information_s_fields() throws Throwable {
		jobsPage.viewJobs();
		jobsPage.selectAJob();
		jobsPage.fillFormJob();
		jobsPage.sendCV();
	}

	@Then("^I should see a sucessfull job apply message$")
	public void i_should_see_a_sucessfull_job_apply_message() throws Throwable {
		assertThat(jobsPage.getSucessfullMessage()).isEqualToIgnoringWhitespace("Thank you for applying.");	
	}

	@When("^I dont fill the firstName-Job$")
	public void i_dont_fill_the_firstName_Job() throws Throwable {
	    jobsPage.clearTxtFirstName();
	    jobsPage.sendCV();
	}

	@Then("^The firstName-Job should be marked as required$")
	public void the_firstName_Job_should_be_marked_as_required() throws Throwable {
		 assertThat(jobsPage.getMessageRequired()).isEqualToIgnoringWhitespace("First Name is required.");
	}

	@When("^I dont fill the lastName-Job$")
	public void i_dont_fill_the_lastName_Job() throws Throwable {
	    jobsPage.clearTxtLastName();
	    jobsPage.sendCV();
	}

	@Then("^The lastName-Job should be marked as required$")
	public void the_lastName_Job_should_be_marked_as_required() throws Throwable {
		 assertThat(jobsPage.getMessageRequired()).isEqualToIgnoringWhitespace("Last Name is required.");
	}

	@When("^I dont fill the email-Job$")
	public void i_dont_fill_the_email_Job() throws Throwable {
	    jobsPage.clearTxtEmail();
	    jobsPage.sendCV();
	}

	@Then("^The email-Job should be marked as required$")
	public void the_email_Job_should_be_marked_as_required() throws Throwable {
		 assertThat(jobsPage.getMessageRequired()).isEqualToIgnoringWhitespace("Email is required.");
	}
	@When("^I dont fill the phone-Job$")
	public void i_dont_fill_the_phone_Job() throws Throwable {
		jobsPage.clearPhone();
		jobsPage.sendCV();
	}

	@Then("^The phone-Job should be marked as required$")
	public void the_phone_Job_should_be_marked_as_required() throws Throwable {
		 assertThat(jobsPage.getMessageRequired()).isEqualToIgnoringWhitespace("Phone is required.");
	}


	@After
	public void tearDown(Scenario cenario) throws IOException{
		Util util = new Util();
		if(cenario.isFailed()) {
			util.takeScreeShot(driver, cenario);
		}
		driver.close();
	}
}
