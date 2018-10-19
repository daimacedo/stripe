package util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.trivago.rta.json.pojo.Report;
import com.trivago.rta.rendering.ReportGenerator;

import cucumber.api.Scenario;

public class Util {
	public void waitVisibilityOfElement(WebElement e, WebDriverWait wait, WebDriver driver){
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void waitUntilElementTobeClickAble(WebElement e, WebDriverWait wait, WebDriver driver){
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	
	public void webSite(WebElement e, WebDriverWait wait, WebDriver driver){
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	public void waitVisibilityOfListOfElements(List<WebElement> e, WebDriverWait wait, WebDriver driver){
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>)e));
	}
	
	public void takeScreeShot(WebDriver driver, Scenario cenario) throws IOException {
		
		SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		Calendar data = Calendar.getInstance();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("target/screenshots/" + cenario.getName().toString() + "_" + formatoData.format(data.getTime()).toString() + ".png"));
	
	}
	
	public void lerArquivo(String path, Properties prop) {

		InputStream fs;
		try {
			fs = getClass().getClassLoader().getResourceAsStream(path);
			prop.load(fs);
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	

}
