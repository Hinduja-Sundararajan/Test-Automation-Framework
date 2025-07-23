package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utilities.BrowserUtilities;

public final class LoginPage extends BrowserUtilities {
	
	private static final By EMAIL_TEXT_BOX_LOCATOR= By.id("email");
	private static final By PASSWORD_TEXT_BOX_LOCATOR= By.id("passwd");
	private static final By SUBMIT_BUTON_LOCATOR= By.id("SubmitLogin");

	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	

	public MyAccountPage doLoginWith(String emailAddress,String password) {
		enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
		enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
		clickOn(SUBMIT_BUTON_LOCATOR);
		MyAccountPage myAccountPage= new MyAccountPage(getDriver());
		return myAccountPage;
	}
	
	
	
}
