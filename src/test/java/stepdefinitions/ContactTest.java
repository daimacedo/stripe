package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import static org.assertj.core.api.Assertions.*;

import java.awt.Image;
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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hooks.SetUp;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ContactPage;
import pages.HomePage;
import util.Util;

public class ContactTest {
	HomePage homePage;
	ContactPage contactPage;
	WebDriver driver;

	public ContactTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
		contactPage = new ContactPage(driver);
	}

	@Given("^I am on the home page$")
	public void i_am_on_the_home_page() throws Throwable {
		driver.get(URLs.HOME_URL);
	}

	@When("^I acess contact sale page$")
	public void i_acess_contact_sale_page() throws Throwable {
		homePage.contactSale();
	}

	@When("^Fill the information's fields$")
	public void fill_the_information_s_fields() throws Throwable {
		contactPage.fillFormContact();
		contactPage.sendContactMail();
	}

	@When("^I should see a sucessfull contact message$")
	public void i_should_see_a_sucessfull_contact_message() throws Throwable {

		assertThat(contactPage.getMessageContactSucessfull().toString())
				.isEqualToIgnoringCase("Thank You!/TESTE@TESTE.COM");

	}

	@When("^I dont fill the firstName$")
	public void i_dont_fill_the_firstName() throws Throwable {
		contactPage.clearFirstName();

	}

	@When("^I dont fill the lastName$")
	public void i_dont_fill_the_lastName() throws Throwable {
		contactPage.clearLastName();

	}

	@When("^I dont fill the email$")
	public void i_dont_fill_the_email() throws Throwable {
		contactPage.clearEmail();

	}

	@When("^I dont fill the site$")
	public void i_dont_fill_the_site() throws Throwable {
		contactPage.clearWebSite();

	}

	@When("^I dont fill the more$")
	public void i_dont_fill_the_more() throws Throwable {
		contactPage.clearMoreInformation();

	}

	@Then("^The firstName should be marked as required$")
	public void the_firstName_should_be_marked_as_required() throws Throwable {
		assertThat(contactPage.isFirstNameRequired()).isTrue();
	}

	@Then("^The lastName should be marked as required$")
	public void the_lastName_should_be_marked_as_required() throws Throwable {
		assertThat(contactPage.isLastNameRequired()).isTrue();
	}

	@Then("^The email should be marked as required$")
	public void the_email_should_be_marked_as_required() throws Throwable {
		assertThat(contactPage.isEmailRequired()).isTrue();
	}

	@Then("^The site should be marked as required$")
	public void the_site_should_be_marked_as_required() throws Throwable {
		assertThat(contactPage.isWebsiteRequired()).isTrue();
	}

	@Then("^The more should be marked as required$")
	public void the_more_should_be_marked_as_required() throws Throwable {
		assertThat(contactPage.isMessageRequired()).isTrue();
	}

	@After
	public void tearDown(Scenario cenario) throws IOException{
		if(cenario.isFailed()) {
			SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMddHH:mm:ss");
			Calendar data = Calendar.getInstance();
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("target/screenshots/" + cenario.getName().toString() + "_"
					+ formatoData.format(data.getTime()).toString() + ".png"));
		}
		driver.close();
	}

}