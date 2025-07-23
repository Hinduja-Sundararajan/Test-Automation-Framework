package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utilities.CSVReaderUtility;
import com.utilities.ExcelReaderUtility;

public class LoginDataProvider {

	@DataProvider(name="LoginTestDataProvider")
	public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
		
		Gson gson=new Gson();
		File testDataFile = new File(System.getProperty("user.dir")+"\\testData\\logindata.json");
		FileReader testDataReader= new FileReader(testDataFile);
		TestData testData=gson.fromJson(testDataReader, TestData.class);
		
		List<Object[]> dataToReturn= new ArrayList<Object[]>();
		for(User user: testData.getData()) {
			dataToReturn.add(new Object[] {user});
		}
		return dataToReturn.iterator();
		
		
	}
	
	@DataProvider(name="LoginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtility.readCSVFile("logindata.csv");
	}
	
	
	@DataProvider(name="LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		return ExcelReaderUtility.readExcelFile("logindata");
	}
	
	
}
