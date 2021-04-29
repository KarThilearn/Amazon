package TestngAmazon;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Repeated.CommonLib;
import junit.framework.Assert;


public class SingingAmazon extends CommonLib{
	
	CommonLib lib = new CommonLib();
	
	@BeforeTest
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
}
