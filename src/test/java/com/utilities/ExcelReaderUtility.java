package com.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {
	public static Iterator<User> readExcelFile(String fileName) {

		File file = new File(System.getProperty("user.dir") + "//testdata//"+fileName+".xlsx");
		XSSFWorkbook xssfWorkbook = null;
		XSSFSheet xssfSheet;
		Iterator<Row> rowIterator;
		List<User> userlist = null;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		User user;
		try {
			xssfWorkbook = new XSSFWorkbook(file);
			userlist= new ArrayList<User>();
			xssfSheet = xssfWorkbook.getSheet("LoginTestData");
			rowIterator = xssfSheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(), passwordCell.toString());
				userlist.add(user);
			}

			xssfWorkbook.close();

		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return userlist.iterator();
	}
}
