package TestngAmazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Repeated.CommonLib;
import junit.framework.Assert;

public class SingingAmazonAndRedmi extends CommonLib {

	CommonLib lib = new CommonLib();

	
	ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void extentreport() {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/testoutputRed.html");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest("Extent Report");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Browser", "Chrome");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}

	@BeforeTest
	public void Invoking() throws IOException {
		lib.InvokeBrowser();
		test.log(Status.PASS, "Navigated to the specified URL");
		test.log(Status.PASS, "Invoke Browser");
		driver.get(URL);
		test.log(Status.PASS, "URL is sent to Browser");
		
	}

	@Test
	public void SigningHomepage() throws InterruptedException, IOException {

		CssClickElements(AccountLists);

		Thread.sleep(5000);
		CssSendKeysElement(Username, "kk504237@gmail.com");
		CssClickElements(Continue);
		CssSendKeysElement(Password, "Shop@123");
		CssClickElements(Signing);
		test.log(Status.PASS, "Signing Clicked");
		Assert.assertEquals(CssGetText(UserEntered), "Hello, karthi");
		test.log(Status.PASS, "Logged Success");
		Screenshot("Login");
	}

	@Test(dependsOnMethods = { "SigningHomepage" })
	public void RedmiMobile() throws IOException {
		CssSendKeysElement(ProductSearch, "Redmi 10+ Pro");
		CssClickElements(SearchButton);
		test.log(Status.PASS, "Redmi 10+ Pro is entered in search");
		Screenshot("Redmi");
		File fle = new File("C:/Users/MK/Desktop/TestData.xlsx");
		FileInputStream Fin = new FileInputStream(fle);
		XSSFWorkbook wbk = new XSSFWorkbook(Fin);
		XSSFSheet sh = wbk.getSheetAt(0);

		List<String> Nomofproducts = listofproducts(RedmiMobiles);
		List<String> NomofProdPrice = listofproducts(RedmiMobPrice);
		// Map<String,String> ProdPrice = new HashMap<String,String>();
		test.log(Status.PASS, "List of Redmi 10+ Pro is available");
		Screenshot("ListofRedmi");
		for (int i = 0; i < Nomofproducts.size(); i++) {
			// ProdPrice.put(Nomofproducts.get(i),NomofProdPrice.get(i));
			String Value = Nomofproducts.get(i);
			String PriceValue = NomofProdPrice.get(i);
			sh.getRow(i + 1).createCell(0).setCellValue(Value);
			sh.getRow(i + 1).createCell(1).setCellValue(PriceValue);
			// sh.getRow(i+1).createCell(0).setCellValue(Nomofproducts.get(i));
			// sh.getRow(i+1).createCell(1).setCellValue(ProdPrice.get(NomofProdPrice.get(i)));

			Fin.close();
			FileOutputStream outputStream = new FileOutputStream(fle);
			wbk.write(outputStream);
			outputStream.close();
			test.log(Status.PASS, "Redmi 10+ Pro is entered in Excel");
		}
	}

	@AfterSuite
	public static void endTest() {
		extent.flush();
	}
}
