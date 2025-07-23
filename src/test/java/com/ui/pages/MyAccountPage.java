package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utilities.BrowserUtilities;

public final class MyAccountPage extends BrowserUtilities {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	private static final By USER_NAME_LOCATOR = By.xpath("//a[@title='View my customer account']/span");
	
	public String getUserName() {
		return getVisibleText(USER_NAME_LOCATOR);
	}

	
}
