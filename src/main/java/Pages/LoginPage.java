package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.SeleniumUtils;

public class LoginPage {
	private WebDriver driver;
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement username;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginbtn;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);//initialiseElements
	}
	
	public void inputUsername(String name) {
		SeleniumUtils.seleniumSendKeys(driver, username, name);
	}
	
	public void inputpassword(String pass) {
		SeleniumUtils.seleniumSendKeys(driver, password, pass);
	}
	
	public void clickloginbtn() {
		SeleniumUtils.click(driver, loginbtn);
	}
	public boolean IsusernamefieldDisplayed() {
		return SeleniumUtils.seleniumIsDisplayed(driver, username);
	}
	
	public boolean IspasswordfieldDisplayed() {
		return SeleniumUtils.seleniumIsDisplayed(driver, password);
	}
	
	public boolean IsloginbuttonDisplayed() {
		return SeleniumUtils.seleniumIsDisplayed(driver, loginbtn);
	}
	
	public String getloginpageTitle() {
		return driver.getTitle();
	}
	
	public void login(String name , String pass) throws InterruptedException {
		inputUsername(name);
		Thread.sleep(5000);
		inputpassword(pass);
		Thread.sleep(5000);
		clickloginbtn();
	}
	
	public void invalidlogin(String name , String pass) {
		inputUsername(name);
		inputpassword(pass);
		clickloginbtn();
	}

}
