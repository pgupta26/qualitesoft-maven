package test.java.com.qualitesoft.freightclub.testscripts.manageorders;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestZPL extends InitializeTest {

	@Test
	public void testZPL() {			
		ManagerOrderPage manageOrderPage =new ManagerOrderPage(driver);

		Xls_Reader xr=new Xls_Reader(testData); 
		int i=Integer.parseInt(Row);
		String orderId=xr.getCellData("Input","OrderId", i).trim();

		WaitTool.sleep(2);
		SeleniumFunction.click(manageOrderPage.ActionButton());
		SeleniumFunction.click(manageOrderPage.shippingLabelsZPL());
		WaitTool.sleep(10); 
		ScreenShot.takeScreenShot(driver, "Shipping labels ZPL for order id "+orderId+ "downloaded.");

		String fileName = orderId+".txt";
		String downloadDir = System.getProperty("user.dir") +File.separator+"download"+File.separator;
		File file = new File(downloadDir+fileName);

		if(file.exists()) {
			Assert.assertTrue(true);
		}else {
			Assert.fail("File doesn't exists");
		}
	}
}
