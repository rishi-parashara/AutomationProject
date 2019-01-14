package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class EnterTimetrackPage {
	// declaration
	@FindBy(xpath = "//div[contains(text(),'Help')]")
	private WebElement helpMenu;
	
	@FindBy(linkText = "About your actiTIME")
	private WebElement aboutUrActiTimeMenu;
	
	@FindBy(xpath = "//td[contains(text(),'Product Version:')]/..//span[contains(text(),'actiTIME')]")
	private WebElement productVersion;
	
	@FindBy(id = "aboutPopupCloseButtonId")
	private WebElement closeBTN;
	
	@FindBy(id = "logoutLink")
	private WebElement logoutLink;
	

	// initialization
	public EnterTimetrackPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public void clickOnHelp(){
		helpMenu.click();
	}
	
	public void clickOnAboutActitime(){
		aboutUrActiTimeMenu.click();
	}
	
	public void clickOnClose(){
		closeBTN.click();
	}
	
	public void clickOnLogout(){
		logoutLink.click();
	}
	
	public void verifyProductVersion(String eText){
		if(productVersion.isDisplayed()){
			Reporter.log("Product version is displayed.",true);
			String aText = productVersion.getText();
			if(aText.equals(eText)){
				Reporter.log("Product version is matching. It is: " + aText + " PASS",true);
			}
			else{
				Reporter.log("Product version is not matching. FAIL",true);
			}
		}
		else{
			Reporter.log("Product version is not displayed.",true);
		}
	}
}
