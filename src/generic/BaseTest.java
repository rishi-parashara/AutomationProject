package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners(Result.class)
public abstract class BaseTest implements IAutoConst{
	
	static
	{
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(FIREFOX_KEY, FIREFOX_VALUE);
	}
	
	public WebDriver driver;
	public String url = Utility.getPropertyData(CONFIG_PATH, "URL");
	
	public String ito = Utility.getPropertyData(CONFIG_PATH, "ITO");
	long lngITO = Long.parseLong(ito);
	
	public String eto = Utility.getPropertyData(CONFIG_PATH, "ETO");
	
	
	@Parameters({"ip","browser"})
	@BeforeMethod(alwaysRun=true)
	public void openApp(@Optional("localhost") String ip, @Optional("chrome")String browser) {
		driver = Utility.openBrowser(ip, browser);
		driver.manage().timeouts().implicitlyWait(lngITO, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApp(ITestResult result){
		int status = result.getStatus();
		String name = result.getName();
		if(status==2){
			String path = Utility.takePhoto(driver, PHOTO_PATH);
			Reporter.log("Test name:- " + name + " FAILED.",true);
			Reporter.log("Photo taken & is available in " + path,true);
		}
		else{
			Reporter.log("Test name:- " + name + " PASSED.",true);
		}
		driver.quit();
	}
		
}
