package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Util;

public class ContactPage {

	WebDriver driver;
	WebDriverWait wait;
	Properties po = new Properties();
	Util util;

	@FindBy(id = "firstname")
	private WebElement txtFirstName;
	@FindBy(id = "lastname")
	private WebElement txtLastName;
	@FindBy(id = "email")
	private WebElement txtEmail;
	@FindBy(id = "website")
	private WebElement txtWebsite;
	@FindBy(xpath = "//div[@class='select-wrapper'][1]")
	private WebElement slctCountry;
	@FindBy(xpath = "//input[@class='searchable-select-input']")
	private WebElement txtSearchCountry;
	@FindBy(xpath = "//div[@class='form-row select volume']")
	private WebElement slctPay;
	@FindBy(xpath = "//li[contains(text(),'Less than')]")
	private WebElement amount;
	@FindBy(id = "message")
	private WebElement txtMessage;
	@FindBy(xpath = "//h1[@class='success-headline']")
	private WebElement labelSucessMsg;
	@FindBy(xpath = "//span[@class='submitted-email']")
	private WebElement labelSucessEmail;
	@FindBy(xpath = "//input[@value='Contact Sales']")
	private WebElement btnContact;

	public ContactPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util.lerArquivo("properties/ContactInfo.properties", po);
		
	}

	private void fillFirstName(String firstName) {
		util.waitVisibilityOfElement(txtFirstName, wait, driver);
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

	private void fillWebSite(String webSite) {
		util.waitVisibilityOfElement(txtWebsite, wait, driver);
		txtWebsite.sendKeys(webSite);

	}

	private void selectCountry(String country) throws InterruptedException {
		slctCountry.click();
		txtSearchCountry.sendKeys(country);
		txtSearchCountry.sendKeys(Keys.ENTER);
	}

	private void selectPay() throws InterruptedException {
		util.waitUntilElementTobeClickAble(slctPay, wait, driver);
		slctPay.click();
		util.waitUntilElementTobeClickAble(amount, wait, driver);
		amount.click();
	}

	private void fillMessage(String msg) {
		util.waitVisibilityOfElement(txtMessage, wait, driver);
		txtMessage.sendKeys(msg);

	}

	public StringBuilder getMessageContactSucessfull() {
		util.waitVisibilityOfElement(labelSucessEmail, wait, driver);
		StringBuilder teste = new StringBuilder();
		teste.append(labelSucessMsg.getText()).append("/").append(labelSucessEmail.getText());
		return teste;
	}

	public void clearFirstName() {
		txtFirstName.clear();
	}

	public void clearLastName() {
		txtLastName.clear();
	}

	public void clearEmail() {
		txtEmail.clear();
	}

	public void clearWebSite() {
		txtWebsite.clear();
	}

	public void clearMoreInformation() {
		txtMessage.clear();
	}

	public boolean isFirstNameRequired() {
		WebElement e = driver
				.findElement(By.xpath("//input[contains(@id,'firstname') and contains(@class,'invalid-missing')]"));
		if (e.isDisplayed()) {
			return true;
		}
		return false;

	}
	
	public boolean isLastNameRequired() {
		WebElement e = driver
				.findElement(By.xpath("//input[contains(@id,'lastname') and contains(@class,'invalid-missing')]"));
		if (e.isDisplayed()) {
			return true;
		}
		return false;

	}

	public boolean isEmailRequired() {
		WebElement e = driver
				.findElement(By.xpath("//input[contains(@id,'email') and contains(@class,'invalid-missing')]"));
		if (e.isDisplayed()) {
			return true;
		}
		return false;

	}
	public boolean isWebsiteRequired() {
		WebElement e = driver
				.findElement(By.xpath("//input[contains(@id,'website') and contains(@class,'invalid-missing')]"));
		if (e.isDisplayed()) {
			return true;
		}
		return false;

	}
	
	public boolean isMessageRequired() {
		WebElement e = driver
				.findElement(By.xpath("//textarea[contains(@id,'message') and contains(@class,'invalid-missing')]"));
		if (e.isDisplayed()) {
			return true;
		}
		return false;

	}

	public void fillFormContact() throws InterruptedException {
		fillFirstName(po.getProperty("FIRSTNAME"));
		fillLastName(po.getProperty("LASTNAME"));
		fillEmail(po.getProperty("EMAIL"));
		fillWebSite(po.getProperty("WEBSITE"));
		selectCountry(po.getProperty("COUNTRY"));
		selectPay();
		fillMessage(po.getProperty("MESSAGE"));
		
	}
	
	public void sendContactMail() {
		btnContact.click();
	}

}
