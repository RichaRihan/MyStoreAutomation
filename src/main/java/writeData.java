import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class writeData {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.google.com/");
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/mystore/qa/testdata/MyStoreData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		
		Sheet sheet = workbook.getSheet("TestSheet");
		
		for(int i=0; i<sheet.getLastRowNum();i++) {
			Row row = sheet.getRow(i+1);
			Cell cell = row.createCell(2);
			cell.setCellValue("Passed");
			FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/src/main/java/com/mystore/qa/testdata/MyStoreData.xlsx");
			workbook.write(fos);
			fos.close();
			System.out.println("END OF WRITING DATA IN EXCEL");
		}
		
		System.out.println("END OF WRITING DATA IN EXCEL");
		
		
	}
	

}
