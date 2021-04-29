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
import Repeated.CommonLib;
import junit.framework.Assert;


public class SingingAmazonAndRedmi extends CommonLib{
	
	CommonLib lib = new CommonLib();
	
	@BeforeSuite
	public void Invoking() {
		lib.InvokeBrowser();
		driver.get(URL);
	}

	@Test
	public void SigningHomepage() throws InterruptedException {
	
		CssClickElements(AccountLists);
	
		Thread.sleep(5000);
		CssSendKeysElement(Username,"kk504237@gmail.com");
		CssClickElements(Continue);
		CssSendKeysElement(Password,"Shop@123");
		CssClickElements(Signing);
		Assert.assertEquals(CssGetText(UserEntered), "Hello, karthi");
	}
	
	@Test(dependsOnMethods={"Invoking","SigningHomepage"})
	public void RedmiMobile() throws IOException {
		CssSendKeysElement(ProductSearch, "Redmi 10+ Pro");
		CssClickElements(SearchButton);

		File fle = new File("C:/Users/MK/Desktop/TestData.xlsx");
		FileInputStream Fin = new FileInputStream(fle);
		XSSFWorkbook wbk = new XSSFWorkbook(Fin);
		XSSFSheet sh = wbk.getSheetAt(0);

		List<String> Nomofproducts = listofproducts(RedmiMobiles);
		List<String> NomofProdPrice = listofproducts(RedmiMobPrice);
		//Map<String,String> ProdPrice = new HashMap<String,String>();
		
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

		}
	}
}
