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
import page.EnterTimetrackPage;
import page.LoginPage;

public class TestVerifyProductVersion extends BaseTest{
	
	@Test(priority=3,groups="smoke")
	public void testVerifyProductVersion(){
		String un = Utility.getXLdata(DATA_PATH, "TestValidLogin", 1, 0);
		String pw = Utility.getXLdata(DATA_PATH, "TestValidLogin", 1, 1);
		String eProductVersion = Utility.getXLdata(DATA_PATH, "TestVerifyProductVersion", 1, 2);
		LoginPage lp = new LoginPage(driver);
		
		//enter user name, password and click login
		lp.setUsername(un);
		lp.setPassword(pw);
		lp.clickLogin();
		
		//click on Help menu
		WebGeneric.verifyTitle(driver, eto, "actiTIME - Enter Time-Track");
		
		EnterTimetrackPage e = new EnterTimetrackPage(driver);
		e.clickOnHelp();
		e.clickOnAboutActitime();
		
		//verify product version
		e.verifyProductVersion(eProductVersion);
		
		e.clickOnClose();
		e.clickOnLogout();

	}
}
