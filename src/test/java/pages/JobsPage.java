package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Util;

public class JobsPage {

	WebDriver driver;
	WebDriverWait wait;
	Util util;
	Properties po = new Properties();

	@FindBy(xpath = "//a[@href='#openings']")
	private WebElement linkViewJobs;
	@FindBy(xpath = "//a[@href='#it']")
	private WebElement menuItJobs;
	@FindBy(xpath = "//section[@id='openings-it']/ul[@class='positions-list']/li")
	private List<WebElement> listItPositions;
	@FindBy(xpath = "//span[.='Apply now']")
	private WebElement btnApplyNow;
	@FindBy(id = "first_name")
	private WebElement txtFirstName;
	@FindBy(id = "last_name")
	private WebElement txtLastName;
	@FindBy(id = "email")
	private WebElement txtEmail;
	@FindBy(id = "phone")
	private WebElement txtPhone;
	@FindBy(xpath = "//a[@data-source='attach'][1]")
	private WebElement linkAttachCV;
	@FindBy(id = "job_application_answers_attributes_4_text_value")
	private WebElement txtArea;
	@FindBy(id = "submit_app")
	private WebElement btnSubmit;
	@FindBy(xpath = "//div[@class='field-error-msg']")
	private WebElement required;

	public JobsPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util.lerArquivo("properties/User.properties", po);
	}

	public void viewJobs() {
		util.waitUntilElementTobeClickAble(linkViewJobs, wait, driver);
		linkViewJobs.click();
	}

	public void selectAJob() {
		util.waitUntilElementTobeClickAble(menuItJobs, wait, driver);
		menuItJobs.click();
		System.out.println("Tamanho lista: " + listItPositions.size());
		for (int i = 0; i < listItPositions.size(); i++) {
			List<WebElement> teste = driver
					.findElements(By.xpath("//section[@id='openings-it']/ul[@class='positions-list']/li/a/h3"));
			String name = teste.get(i).getText();
			if (name.contains("IT Operations")) {
				listItPositions.get(i).click();
			}

		}
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", btnApplyNow);
	}

	private void fillFirstName(String firstName) {
		// util.waitVisibilityOfElement(txtFirstName, wait, driver);
		driver.switchTo().frame(0);
		txtFirstName.sendKeys(firstName);
	}

	private void fillLastName(String lastName) {
		util.waitVisibilityOfElement(txtLastName, wait, driver);
		txtLastName.sendKeys(lastName);
	}

	private void fillEmail(String email) {
		util.waitVisibilityOfElement(txtEmail, wait, driver);
		txtEmail.sendKeys(email);
	}

	private void fillPhone(String phone) {
		util.waitVisibilityOfElement(txtPhone, wait, driver);
		txtPhone.sendKeys(phone);
	}

	private void workRemotly() {

		Select remotly = new Select(driver.findElement(By.id("job_application_answers_attributes_3_boolean_value")));
		remotly.selectByVisibleText("Yes");
	}

	private void fillTxtArea(String text) {
		util.waitVisibilityOfElement(txtArea, wait, driver);
		txtArea.sendKeys(text);

	}


	public String getSucessfullMessage() {
		return driver.findElement(By.xpath("//div[@id='application_confirmation']/h1[.='Thank you for applying.']"))
				.getText();
	}

	public void fillFormJob() {
		fillFirstName(po.getProperty("FIRSTNAME"));
		fillLastName(po.getProperty("LASTNAME"));
		fillEmail(po.getProperty("EMAIL"));
		fillPhone(po.getProperty("PHONE"));
		workRemotly();
		fillTxtArea(po.getProperty("TEXT"));

	}

	public void clearTxtFirstName() {
		txtFirstName.clear();
	}

	public void clearTxtLastName() {
		txtLastName.clear();
	}

	public void clearTxtEmail() {
		txtEmail.clear();
	}

	public void clearPhone() {
		txtPhone.clear();
	}

	public void sendCV() {
		btnSubmit.click();
	}

	public String getMessageRequired() {
		return required.getText();
	}

}
