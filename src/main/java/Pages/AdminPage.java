package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.SeleniumUtils;
//This is Admin Page
public class AdminPage {
	
	private WebDriver driver ;
	
	@FindBy(xpath = "//span[contains(text(),'User Management')")
	private WebElement usermanagement;
	
	@FindBy(xpath = "//span[contains(text(),'Organization')]")
	private WebElement organization;
	
	@FindBy(xpath = "//span[contains(text(),'Configuration')]")
	private WebElement config;
	
	
	
	public AdminPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public boolean VisiblityofUserManagementTab() {
		return SeleniumUtils.seleniumIsDisplayed(driver, usermanagement);
	}
	
	public boolean VisiblityoforganizationTab() {
		return SeleniumUtils.seleniumIsDisplayed(driver, organization);
	}
	
	public boolean Visiblityofconfig() {
		return SeleniumUtils.seleniumIsDisplayed(driver, config);
	}
}
