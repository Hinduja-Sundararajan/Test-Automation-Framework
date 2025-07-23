package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utilities.BrowserUtilities;
import com.utilities.JSONUtility;

import static com.utilities.PropertiesUtil.*;

public final class HomePage extends BrowserUtilities {
	
	public HomePage(String browserName) {
		super(browserName);
		goToWebsite(readProperty(QA, "URL"));
	}

	public HomePage(Browser browserName) {
		super(browserName);
		goToWebsite("http://automationpractice.pl");
	}
	
	public HomePage(Browser browserName, boolean isheadless) {
		super(browserName,isheadless);
		goToWebsite("http://automationpractice.pl");
	}

	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(JSONUtility.readJson("QA").getUrl());
	}

	private static final By SIGN_IN_LINK_LOCATOR= By.xpath("//a[contains(text(),'Sign')]");
	
	public LoginPage goToLoginPage() {
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage= new LoginPage(super.getDriver());
		return loginPage;
	}

	public void quit() {
		closeBrowser();
		
	}

	
	
}
