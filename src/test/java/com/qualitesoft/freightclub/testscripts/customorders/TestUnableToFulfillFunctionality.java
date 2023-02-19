package test.java.com.qualitesoft.freightclub.testscripts.customorders;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.Log;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import test.java.com.qualitesoft.freightclub.pageobjects.QuickQuote;
import test.java.com.qualitesoft.freightclub.pageobjects.Admin.ManageOrderNotQuotedTab;

public class TestUnableToFulfillFunctionality extends InitializeTest {
	
	@Test
	public void testUnableToFulfillFunctionality() {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		SeleniumFunction.click(manageOrderpage.manageOrdersLink());

		QuickQuote quickQuote = new QuickQuote(driver);
		WaitTool.sleep(20);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);

		ManageOrderNotQuotedTab notQuotedTab = new ManageOrderNotQuotedTab(driver);
		SeleniumFunction.click(notQuotedTab.notQuoted());
		WaitTool.sleep(5);

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		Log.info("Data Row: " +Row);
		
		String orderid=xr.getCellData("Input","OrderId", i).trim();
		SeleniumFunction.sendKeys(notQuotedTab.orderIDFilter(), orderid);
		WaitTool.sleep(5);
		notQuotedTab.orderIDFilter().sendKeys(Keys.ENTER);
		WaitTool.sleep(5);
		
		SeleniumFunction.click(notQuotedTab.complete());
		WaitTool.sleep(5);

		SeleniumFunction.click(notQuotedTab.unableToFulfil());
		WaitTool.sleep(30);
		
		Assert.assertEquals(notQuotedTab.gridStatus().getText().trim(), "Unable To Fulfill");
		ScreenShot.takeScreenShot(driver, "Unable to fulfil");

	}
}
