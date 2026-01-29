package Tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;



import Base.BaseTest;
import Config.ConfigReader;
import Pages.LoginPage;
import Utility.ExcelUtils;

@Listeners(Listener.TestListener.class)
public class LoginTest extends BaseTest {
	LoginPage loginpage;
	SoftAssert soft;
	String dataFilepath = System.getProperty("user.dir")+"/src/test/testdata/dataFile.xlsx";
	
	@Test(priority = 1 , enabled = false)
	public void check_LoginPageTitle() throws InterruptedException {
		ExcelUtils.loadExcel(dataFilepath);
		loginpage = new LoginPage(driver); //Created an Object of login page 
		
		String expectedTitle = ExcelUtils.getdata("LoginCred", 1, 2);
		System.out.println(expectedTitle);
		
		String actualTitle = loginpage.getloginpageTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle , expectedTitle);
		Thread.sleep(3000);
	}
	
	@Test(priority = 2 , enabled = false)
	public void check_ValidLogin() throws InterruptedException, IOException {
		loginpage = new LoginPage(driver); //Initialize
		boolean actualusernamefield = loginpage.IsusernamefieldDisplayed();
		System.out.println(actualusernamefield);
		
		Assert.assertTrue(actualusernamefield);
		
		boolean actualpasswordfield = loginpage.IspasswordfieldDisplayed();
		System.out.println(actualpasswordfield);
		
		Assert.assertTrue(actualpasswordfield);  //fail
		
		boolean actualloginbtnfield = loginpage.IsloginbuttonDisplayed();
		System.out.println(actualloginbtnfield);
		
		Assert.assertTrue(actualloginbtnfield);
		
		loginpage.login(ConfigReader.get("userid") , ConfigReader.get("password"));
		Thread.sleep(3000);
	}
	
	@Test(priority = 3 , enabled = true)
	public void check_ValidLoginWithSoftAssert() throws InterruptedException, IOException {
		soft = new SoftAssert();
		loginpage = new LoginPage(driver);
		boolean actualusernamefield = loginpage.IsusernamefieldDisplayed();
        AssertJUnit.assertTrue(actualusernamefield);
        System.out.println(actualusernamefield);
        
        boolean actualpasswordfield = loginpage.IspasswordfieldDisplayed();
        AssertJUnit.assertFalse(actualpasswordfield);
        System.out.println(actualpasswordfield);
        
        boolean actualloginbtnfield = loginpage.IsloginbuttonDisplayed();
        AssertJUnit.assertTrue(actualloginbtnfield);
        System.out.println(actualloginbtnfield);
        
        loginpage.login(ConfigReader.get("userid") , ConfigReader.get("password"));
        soft.assertAll("Assertion Execute");
        
        Thread.sleep(3000);
	}
	
	@DataProvider
	public Object[][] getDataForInvalidLogin() {
		ExcelUtils.loadExcel(dataFilepath);
		return ExcelUtils.getSheetData("DataDriven");
	}

	@Test(priority=4, enabled=true, dataProvider="getDataForInvalidLogin")
	public void checkInvalidLoginWithMulipleSet(String username, String password) throws InterruptedException {
		ExcelUtils.loadExcel(dataFilepath);
		loginpage = new LoginPage(driver);
		loginpage.login(username, password);
	}
}
