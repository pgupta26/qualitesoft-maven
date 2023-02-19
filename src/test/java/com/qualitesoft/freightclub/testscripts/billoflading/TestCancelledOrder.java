package test.java.com.qualitesoft.freightclub.testscripts.billoflading;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.UseAssert;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.pageobjects.BillOfLadingPage;
import test.java.com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestCancelledOrder extends InitializeTest{

	@Test
	public void verifyCancelledOrderStatus(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		Xls_Reader xr=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
		int i=Integer.parseInt(Row);
		
		SeleniumFunction.clickJS(driver, bolPage.billOfLading());
		WaitTool.sleep(2);

		if(manageOrderpage.acceptFeedbackPopupStatus() == true){
			manageOrderpage.acceptFeedbackPopup();
		}

		String orderid=xr.getCellData("BOL","OrderId", i).trim();
		SeleniumFunction.sendKeys(bolPage.orderIdTextBox(), orderid);
		bolPage.orderIdTextBox().sendKeys(Keys.ENTER);
		/*SeleniumFunction.KeyBoradEnter(driver);*/
		WaitTool.sleep(15);
		ScreenShot.takeScreenShot(driver, "BillofLadingPage for cancelled order "+ orderid);
		String orderStatus = SeleniumFunction.getText(bolPage.gridData(1, 5));
		UseAssert.assertEquals(orderStatus, "Order Cancelled");
	}
}
