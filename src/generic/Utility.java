package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Utility {
	
	public static String getXLdata(String path, String testName, int r, int c){
		String value = "";
		try{
			Workbook w = WorkbookFactory.create(new FileInputStream(path));
			value = w.getSheet(testName).getRow(r).getCell(c).getStringCellValue();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getPropertyData(String path, String key){
		String value = "";
		
		try {
			Properties p = new Properties();
			p.load(new FileInputStream(path));
			value = p.getProperty(key);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static void writeResultToXL(String RES_PATH, int passCount, int failCount){
		try{
			Workbook w = WorkbookFactory.create(new FileInputStream(RES_PATH));
			w.getSheet("Sheet1").getRow(1).getCell(0).setCellValue(passCount);
			w.getSheet("Sheet1").getRow(1).getCell(1).setCellValue(failCount);
			w.write(new FileOutputStream("./result/Summary.xlsx"));
			w.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static WebDriver openBrowser(String ip, String browser){
		WebDriver driver = null;
		if(ip.equals("localhost")){
			if(browser.equals("chrome")){
				driver = new ChromeDriver();
			}
			else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
			}
		}
		else{
			//open in RWD
			try{ 
				URL remoteAddress = new URL("http://"+ip+":4444/wd/hub");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName(browser);
				driver = new RemoteWebDriver(remoteAddress, capabilities);
			}
			catch (Exception e) {
				e.printStackTrace();
				driver = new ChromeDriver();
			}
		}
		
		return driver;
	}
	
	public static String takePhoto(WebDriver driver, String folderName){
		String path="";
		Date d = new Date();
		String ts = d.toString().replaceAll(":", "");
		
		try {
			TakesScreenshot t = (TakesScreenshot)driver;
			File srcFile = t.getScreenshotAs(OutputType.FILE);
			path = folderName+ts+".png";
			File destFile = new File(path);
			FileUtils.copyFile(srcFile, destFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return path;
	}
	
	public static int getXLActiveRowCount(String path, String testName){
		int rowCount = 0;
		try{
			Workbook w = WorkbookFactory.create(new FileInputStream(path));
			rowCount = w.getSheet(testName).getLastRowNum();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rowCount;
	}
	
}
