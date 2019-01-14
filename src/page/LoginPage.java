package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage {
//declaration
	@FindBy(id="username")
	private WebElement unTB;
	
	@FindBy(name="pwd")
	private WebElement pwTB;
	
	@FindBy(xpath="//div[.='Login ']")
	private WebElement loginBTN;
	
	@FindBy(xpath="//span[.='Username or Password is invalid. Please try again.']")
	private WebElement errMessage;

	
	
//initialization
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
//utilization
	public void setUsername(String un){
		unTB.sendKeys(un);
	}
	
	public void setPassword(String pw){
		pwTB.sendKeys(pw);
	}
	
	public void clickLogin(){
		loginBTN.click();
	}
	
	public void verifyErrorMessage(){
		if(errMessage.isDisplayed()){
			Reporter.log("Error message is successfully displayed. PASS",true);
		}
		else{
			Reporter.log("Error message is not displayed. FAIL",true);
			Assert.fail();
		}
	}
	
	public void verifyTitle(WebDriver driver, String eto, String eTitle){
		long lngETO = Long.parseLong(eto);
		WebDriverWait wait = new WebDriverWait(driver, lngETO);
		try{
			wait.until(ExpectedConditions.titleIs(eTitle));
			Reporter.log("Title is matching. It is: "+eTitle+" PASS",true);
		}
		catch(Exception e){
			Reporter.log("Title is not matching. FAIL",true);
			Assert.fail();
		}
	}
	
	
}
