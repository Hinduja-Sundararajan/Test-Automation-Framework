package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.utilities.JSONUtility;

public class MyRetryAnalyzer implements IRetryAnalyzer {

		private static final int MAX_ATTEMPTS= JSONUtility.readJson("QA").getMAX_ATTEMPTS();
		private static int currentAttempt=1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(currentAttempt<MAX_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}

	
	
	
	
}
