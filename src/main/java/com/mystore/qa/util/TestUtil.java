package com.mystore.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.mystore.qa.base.BaseClass;

public class TestUtil extends BaseClass{
	
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;
	
	public static String TESTDATA_SHEET_PATH=System.getProperty("user.dir")+"/src/main/java/com/mystore/qa/testdata/MyStoreData.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	
	public static void loadExcel(){
		FileInputStream file=null;
		try {
			file=new FileInputStream(TESTDATA_SHEET_PATH);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(file);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Object[][] getTestData(String sheetName) {
		loadExcel();
		sheet= book.getSheet(sheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0; j< sheet.getRow(0).getLastCellNum(); j++) {
				
				if(sheet.getRow(i+1).getCell(j).getCellType()==CellType.NUMERIC) {
					data[i][j]= NumberToTextConverter.toText(sheet.getRow(i+1).getCell(j).getNumericCellValue());
				}
				
				else {
				data[i][j]= sheet.getRow(i+1).getCell(j).toString();
				}
			}
		}
		
		return data;
		
	}
	
	public static void writeData(String sheetName, String result, int rowNum , int colNum) throws IOException {
			loadExcel();
			sheet= book.getSheet(sheetName);
			Row row= sheet.getRow(rowNum);
			Cell cell = row.createCell(colNum);
			cell.setCellValue("");
			cell.setCellValue(result);
			FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/src/main/java/com/mystore/qa/testdata/MyStoreData.xlsx");
			book.write(fos);
			fos.close();
			System.out.println("End of writing data in excel");
	
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir= System.getProperty("user.dir");
		FileUtils.copyFile(src, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		
	}
	
	

}
