package BadBlood.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement pass;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css=".toast-message")
	WebElement error;
	
	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void login(String user, String password) {
		username.sendKeys(user);
		pass.sendKeys(password);
		login.click();
	}
	
	public String getErrorMsg() throws InterruptedException {
		Thread.sleep(1000);
		return error.getText();
	}

}
