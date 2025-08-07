package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.constants.Browser;

public abstract class BrowserUtilities {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public BrowserUtilities(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public void goToWebsite(String url) {
		driver.get().get(url);
	}

	public void clickOn(By locator) {
		WebElement element = driver.get().findElement(locator);
		element.click();
	}

	public void enterText(By locator, String text) {
		WebElement element = driver.get().findElement(locator);
		element.sendKeys(text);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public String getVisibleText(By locator) {
		WebElement element = driver.get().findElement(locator);
		return element.getText();

	}

	public BrowserUtilities(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
			driver.get().manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
			driver.get().manage().window().maximize();
		} else {
			System.err.print("Invalid browser name.....Please choose either chrome or edge browser");
		}
	}

	public BrowserUtilities(Browser browserName) {
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
			driver.get().manage().window().maximize();
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
			driver.get().manage().window().maximize();
		}
	}

	public BrowserUtilities(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
				driver.get().manage().window().maximize();
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
				driver.get().manage().window().maximize();
			}
		}
	}

	public String takeScreenhot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotsrc = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path =  "./screenshots/" + name + "-" + timeStamp + ".png";
		File screenshotdst = new File(path);
		try {
			FileUtils.copyFile(screenshotsrc, screenshotdst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	public void closeBrowser() {
		driver.get().quit();
	}

}
