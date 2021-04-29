package TestngAmazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import Repeated.CommonLib;

public class ProductSearch extends CommonLib {

	@Test(dependsOnMethods={"Invoking","SigningHomepage"})
	public void RedmiMobile() throws IOException {
		CssSendKeysElement(ProductSearch, "Redmi 10+ Pro");
		CssClickElements(SearchButton);

		File fle = new File("C:/Users/MK/Desktop/TestData.xlsx");
		FileInputStream Fin = new FileInputStream(fle);
		XSSFWorkbook wbk = new XSSFWorkbook(Fin);
		XSSFSheet sh = wbk.getSheetAt(0);

		List<String> Nomofproducts = listofproducts(RedmiMobiles);

		for (int i = 0; i < Nomofproducts.size(); i++) {
			String Value = Nomofproducts.get(i);
			sh.getRow(i).createCell(1).setCellValue(Value);

			Fin.close();
			FileOutputStream outputStream = new FileOutputStream(fle);
			wbk.write(outputStream);
			outputStream.close();

		}
	}

}