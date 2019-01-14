package generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class WebGeneric {
	public static void verifyTitle(WebDriver driver, String eto, String eTitle){
		Long lngETO = Long.parseLong(eto);
		WebDriverWait wait = new WebDriverWait(driver, lngETO);
		try{
			wait.until(ExpectedConditions.titleContains(eTitle));
			Reporter.log("Title is matching. It is: " + eTitle +" PASS",true);
		}
		catch(Exception e){
			e.printStackTrace();
			Reporter.log("Title is not matching.FAIL",true);
			Assert.fail();
		}
	}
}
