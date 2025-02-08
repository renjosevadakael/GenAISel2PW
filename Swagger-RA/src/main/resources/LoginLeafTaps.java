package automation.seleniumCode;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginLeafTaps {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		
		//Open Chrome
		ChromeDriver driver = new ChromeDriver();
		
		//Maximize 
		driver.manage().window().maximize();
		
		//Load URL
		driver.get("http://www.leaftaps.com/opentaps/control/main");
		
		//Type UserName
		WebElement usrName = driver.findElementById("username");
		usrName.sendKeys("demosalesmanager");
		
		//Type Password
		WebElement passWd = driver.findElementById("password");
		passWd.sendKeys("crmsfa");
		
		//Click Login
		driver.findElementByClassName("decorativeSubmit").click();
		driver.findElementByLinkText("CRM/SFA").click();
		
		
			
		//Close
		driver.close();
		

	}

}