package script;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.representer.Represent;

import generic.BaseTest;
import generic.Result;
import generic.Utility;
import generic.WebGeneric;
import page.LoginPage;

public class TestValidLogin extends BaseTest{
	
	@Test(priority=1,groups="smoke")
	public void testValidLogin(){
		String un = Utility.getXLdata(DATA_PATH, "TestValidLogin", 1, 0);
		String pw = Utility.getXLdata(DATA_PATH, "TestValidLogin", 1, 1);
		String eLoginPageTitle = Utility.getXLdata(DATA_PATH, "TestValidLogin", 1, 2);
		String eHomePageTitle = Utility.getXLdata(DATA_PATH, "TestValidLogin", 1, 3);
		
		LoginPage lp = new LoginPage(driver);
		
		//verify login page is displayed
		lp.verifyTitle(driver, eto, eLoginPageTitle);
				
		
		//enter user name, password and click login
		lp.setUsername(un);
		lp.setPassword(pw);
		lp.clickLogin();
		
		//verify Home page is displayed
		lp.verifyTitle(driver, eto, eHomePageTitle);
	}
}
