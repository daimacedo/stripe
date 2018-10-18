package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Util;

public class HomePage extends Util{
	
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//li//a[@href='/contact/sales']")
	private WebElement btnContactSale;
	
	@FindBy(xpath="//a[@href='https://dashboard.stripe.com/login']/span[text()='Sign in']")
	private WebElement linkSignIn;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public void contactSale(){
		waitUntilElementTobeClickAble(btnContactSale, wait, driver);
		btnContactSale.click();
	}
	

	
	

}
