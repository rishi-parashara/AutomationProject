package script;

import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.LoginPage;

public class TestInvalidLogin extends BaseTest{
	@Test(priority=2, groups="smoke")
	public void testInvalidLogin(){
		
		int rc = Utility.getXLActiveRowCount(DATA_PATH, "TestInvalidLogin");
		
		for (int i = 1; i <= rc; i++) {
			String un = Utility.getXLdata(DATA_PATH, "TestInvalidLogin", i, 0);
			String pw = Utility.getXLdata(DATA_PATH, "TestInvalidLogin", i, 1);
			
			LoginPage lp = new LoginPage(driver);
			
			//enter user name, password and click login
			lp.setUsername(un);
			lp.setPassword(pw);
			lp.clickLogin();
			
			//verify error message is displayed
			lp.verifyErrorMessage();
		}
		
	}
}
