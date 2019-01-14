package generic;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Result implements ITestListener, IAutoConst{
	
	 int passCount = 0, failCount = 0;
	
	@Override
	public void onTestSuccess(ITestResult result) {
		String name = result.getName();
		Reporter.log("Test: " + name + " is PASS.",true);
		passCount++;
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String name = result.getName();
		Reporter.log("Test: " + name + " is FAIL.",true);
		failCount++;
	}
	
	@Override
	public void onFinish(ITestContext context) {
		Reporter.log("Pass count: " + passCount,true);
		Reporter.log("Fail count: " + failCount,true);
		Utility.writeResultToXL(RES_PATH, passCount, failCount);
	}
//--------------------------------------------------------------------------------------
	
	@Override
	public void onTestStart(ITestResult result) {
	}
	@Override
	public void onTestSkipped(ITestResult result) {
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
	@Override
	public void onStart(ITestContext context) {
	}

}
