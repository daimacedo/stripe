package hooks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import constantes.URLs;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SetUp extends URLs {

	protected static WebDriver driver;

	public SetUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

}
