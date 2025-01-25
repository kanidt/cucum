package org.global;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Alert alert;
	public static JavascriptExecutor javaScriptExecutor;
	public void getDriver(String browserType) {
		switch (browserType) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			break;
		}


	}
	public void windowsMax() {
		driver.manage().window().maximize();

	}
	public void getUrl(String url) {
		driver.get(url);
	}
	public void sendKeys(WebElement element,String readExcel) {
		element.sendKeys(readExcel);
		
	}
	public void screenShot() throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File screenshotAs = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile = new File("Image\\"+System.currentTimeMillis()+".jpeg");
		FileUtils.copyFile(screenshotAs, targetFile);
		
	}
	public String readExcel(int rownum ,int cellnum) throws IOException {
		File file = new File("C:\\Users\\DELL\\eclipse-workspace\\workspace\\SwagLabsSingleTon\\BookswagonDemojunit\\src\\test\\resources\\bookswagonexcel.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook book = new XSSFWorkbook(fileInputStream);
		Sheet sheet = book.getSheet("logindata");
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		
		CellType cellType = cell.getCellType();
		
		String value =null;
		
		switch (cellType) {
		case STRING:
			 value = cell.getStringCellValue();
			
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
				 value = simpleDateFormat.format(dateCellValue);
				
				}else {
					double numericCellValue = cell.getNumericCellValue();
					long ref = (long)numericCellValue;
					BigDecimal valueOf = BigDecimal.valueOf(ref);
					value = valueOf.toString();
				
			}
			break;

		default:
			System.out.println("invalid cell type");
			break;
		}
		return value;
	}
	public void clickButton(WebElement element) {
	 element.click();
		
	}

	public void selectByVisibleText(WebElement element, String readExcel) {
        Select select = new Select(element);
        select.selectByVisibleText(readExcel);
    }
	
	public void click(WebElement element) {
		element.click();
	}
	
	public void sleep(int millis) throws InterruptedException {
		Thread.sleep(millis);
	}
	
	
	public void confirmAlert(String values) {
		 Alert a = driver.switchTo().alert();
		switch (values) {
		case "accept":
			a.accept();
			break;
		case"dismiss":
			a.dismiss();
			break;

		default:
			break;
		}
	

	}
	public void navigate(String commands) {
		switch (commands) {
		case "refresh":
			driver.navigate().refresh();
			break;
		case "forward":
			driver.navigate().forward();
			break;

		case "back":
			driver.navigate().back();
			break;

        default:
		driver.navigate().to(commands);
			break;
		}
		
	}
	public void scrollDownByJs( WebElement element ){
		javaScriptExecutor=(JavascriptExecutor)driver;
		javaScriptExecutor.executeScript("arguments[0].scrollIntoView(false)",element);
	}
	public void scrollUpByJs( WebElement element,String scrollType ){
		javaScriptExecutor=(JavascriptExecutor)driver;
		switch(scrollType) {
		case"Up":
			javaScriptExecutor.executeScript("arguments[0].scrollIntoView(false)",element);
			break;
			
		case"Down":
			javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true)",element);
			break;
			
			default:
				System.out.println("Invalid Scroll type");
		}
	System.out.println("deve work");
	System.out.println("deve done");
	System.out.println("qa work");
	System.out.println("qa done");
	
	
	}
}
		
		
		
	



