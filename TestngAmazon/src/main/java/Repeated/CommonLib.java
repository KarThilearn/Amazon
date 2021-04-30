package Repeated;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.Parameters;

public class CommonLib {
	
public static WebDriver driver;
public String URL = "https://www.amazon.in/";
public String AccountLists = "span.nav-line-2.nav-long-width";
public String Signin = "div#nav-flyout-ya-signin a.nav-action-button span.nav-action-inner";
public String Username = "input#ap_email";
public String Continue = "input#continue";
public String Password = "input#ap_password";
public String Signing = "input#signInSubmit";
public String UserEntered = "span#nav-link-accountList-nav-line-1";
public String ProductSearch = "input#twotabsearchtextbox";
public String SearchButton = "input#nav-search-submit-button";
public String RedmiMobiles = "span.a-size-medium.a-color-base.a-text-normal";
public String RedmiMobPrice = "span.a-price-whole";

public ExtentHtmlReporter htmlReporter;
public ExtentReports extent;
public ExtentTest test;



	public WebDriver InvokeBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();  
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		return driver;
	}	

	public boolean CssDisplayFindElement(String Element) {
		return driver.findElement(By.cssSelector(Element)).isDisplayed();
	}
	
	public void XpathFindElement(String Element) {
		driver.findElement(By.xpath(Element));
	}
	
	public void xpathClickElements(String Element) {
		driver.findElement(By.xpath(Element)).click();
	}
	public void CssClickElements(String Element) {
		driver.findElement(By.cssSelector(Element)).click();
	}
	
	public void XpathSendKeysElement(String Element, String Value) {
		driver.findElement(By.xpath(Element)).sendKeys(Value);
	}
	
	public void CssSendKeysElement(String Element, String Value) {
		driver.findElement(By.cssSelector(Element)).sendKeys(Value);
	}
	
	public String CssGetText(String Element) {
		String AmazonText = driver.findElement(By.cssSelector(Element)).getText();
		return AmazonText;
	}
	
	public List<String> listofproducts(String Element) {
		
		List<WebElement> listofprod = driver.findElements(By.cssSelector(Element));
 		List<String> Noofproducts = new ArrayList<>();
 		
 		
 		for(WebElement ProductsList : listofprod) {
			Noofproducts.add(ProductsList.getText());
	}
		return Noofproducts;
	}
	
	public void ExcelConnection() throws IOException {
		
		File fle = new File("C:/Users/MK/Desktop/TestData.xlsx");
		FileInputStream Fin = new FileInputStream(fle);
		XSSFWorkbook wbk = new XSSFWorkbook(Fin);
		XSSFSheet sh = wbk.getSheetAt(0);
		FileOutputStream outputStream=new FileOutputStream(fle);
	}
	
	
	public void extentReports() {
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/testoutput/testoutput.html");
		
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    
	    extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser", "Chrome");
        
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	}
	
}




