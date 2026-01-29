package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.SeleniumUtils;

public class HomePage {
	private WebDriver driver;
	
	@FindBy (xpath = "//div[@class ='oxd-main-menu-search']")
	private WebElement Search;
	
	@FindBy (xpath = "//span[text()='Admin']")
	private WebElement Admin;
	
	@FindBy (xpath = "//span[text()='PIM']")
	private WebElement PIM;
	
	@FindBy (xpath = "//span[text()='Leave']")
	private WebElement Leave;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean VisiblityofAdminTab() {
		return SeleniumUtils.seleniumIsDisplayed(driver, Admin);
	}
	
	public boolean VisiblityofSearch() {
		return SeleniumUtils.seleniumIsDisplayed(driver, Search);
	}
	public boolean VisiblityofPIMTab() {
		return SeleniumUtils.seleniumIsDisplayed(driver, PIM);
	}
	
	public boolean VisiblityofLeaveTab() {
		return SeleniumUtils.seleniumIsDisplayed(driver, Leave);
	}
	
	public void ClickToAdminTab() {
		SeleniumUtils.seleniumClick(driver, Admin);
	}
	public String getPageTitle(){
		return driver.getTitle();
	}
}