package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utilities.BrowserUtilities;
import com.utilities.LambdaTestUtility;
import com.utilities.LoggerUtility;

public class TestBase {
	protected HomePage homePage;
	Logger logger= LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;
	
	@Parameters({"browser","isLambdaTest","isHeadless"})
	@BeforeMethod(description="Load the Homepage of the website")
	public void setup(
			@Optional("chrome") String browser,
			@Optional("false")boolean isLambdaTest,
			@Optional("true")boolean isHeadless,ITestResult result) {
		
		this.isLambdaTest=isLambdaTest;
		
		WebDriver lambdaDriver;
		if(isLambdaTest) {
			lambdaDriver=LambdaTestUtility.intializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage= new HomePage(lambdaDriver);
		}
		else {
		homePage = new HomePage(Browser.valueOf(browser.toUpperCase()),isHeadless);
		}
	}
	
	public BrowserUtilities getInstance() {
		return homePage;
	}
	
	@AfterMethod(description="Tear Down the browser")
	public void tearDown() {
		if(isLambdaTest) {
			LambdaTestUtility.quitSession();
		}
		else {
		homePage.quit();
		}
	}


}
