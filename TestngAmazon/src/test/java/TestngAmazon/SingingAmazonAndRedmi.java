package TestngAmazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Repeated.CommonLib;
import junit.framework.Assert;

public class SingingAmazonAndRedmi extends CommonLib{
	
	CommonLib lib = new CommonLib();
	
	@BeforeTest
	public void Invoking() {
		lib.InvokeBrowser();
		test.log(Status.PASS, MarkupHelper.createLabel("Invoke Browser", ExtentColor.GREEN));
		driver.get(URL);
		test.log(Status.PASS, MarkupHelper.createLabel("URL is sent to Browser", ExtentColor.GREEN));
	}

	@Test
	public void SigningHomepage() throws InterruptedException {
	
		CssClickElements(AccountLists);
		
		Thread.sleep(5000);
		CssSendKeysElement(Username,"kk504237@gmail.com");
		CssClickElements(Continue);
		CssSendKeysElement(Password,"Shop@123");
		CssClickElements(Signing);
		test.log(Status.PASS, MarkupHelper.createLabel("Signing Clicked", ExtentColor.GREEN));
		Assert.assertEquals(CssGetText(UserEntered), "Hello, karthi");
		test.log(Status.PASS, MarkupHelper.createLabel("Logged Success", ExtentColor.GREEN));
	}
	
	@Test(dependsOnMethods={"SigningHomepage"})
	public void RedmiMobile() throws IOException {
		CssSendKeysElement(ProductSearch, "Redmi 10+ Pro");
		CssClickElements(SearchButton);
		test.log(Status.PASS, MarkupHelper.createLabel("Redmi 10+ Pro is entered in search", ExtentColor.GREEN));
		File fle = new File("C:/Users/MK/Desktop/TestData.xlsx");
		FileInputStream Fin = new FileInputStream(fle);
		XSSFWorkbook wbk = new XSSFWorkbook(Fin);
		XSSFSheet sh = wbk.getSheetAt(0);

		List<String> Nomofproducts = listofproducts(RedmiMobiles);
		List<String> NomofProdPrice = listofproducts(RedmiMobPrice);
		//Map<String,String> ProdPrice = new HashMap<String,String>();
		test.log(Status.PASS, MarkupHelper.createLabel("List of Redmi 10+ Pro is available", ExtentColor.GREEN));
		for (int i = 0; i < Nomofproducts.size(); i++) {
			//ProdPrice.put(Nomofproducts.get(i),NomofProdPrice.get(i));
			String Value = Nomofproducts.get(i);
			String PriceValue = NomofProdPrice.get(i);
			sh.getRow(i+1).createCell(0).setCellValue(Value);
			sh.getRow(i+1).createCell(1).setCellValue(PriceValue);
			//sh.getRow(i+1).createCell(0).setCellValue(Nomofproducts.get(i));
			//sh.getRow(i+1).createCell(1).setCellValue(ProdPrice.get(NomofProdPrice.get(i)));
			
			Fin.close();
			FileOutputStream outputStream = new FileOutputStream(fle);
			wbk.write(outputStream);
			outputStream.close();
			test.log(Status.PASS, MarkupHelper.createLabel("Redmi 10+ Pro is entered in Excel", ExtentColor.GREEN));
		}
	}
}
