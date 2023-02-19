package test.java.com.qualitesoft.freightclub.testscripts.manageorders;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.JavaFunction;
import test.java.com.qualitesoft.core.Log;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestExportOrders extends InitializeTest
{

	@Test
	public void exportAllOrdersAndVerifyCount() throws IOException {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		SeleniumFunction.click(manageOrderpage.manageOrdersLink());
		WaitTool.sleep(7);

		if(manageOrderpage.acceptFeedbackPopupStatus() == true){
			manageOrderpage.acceptFeedbackPopup();
		}

		if(keyword.equals("quickOrders") == true){
			SeleniumFunction.click(manageOrderpage.openQuoteTab());
		}else if(keyword.equals("fullOrders") == true){
			Log.info("Already in booked table");
		}

		String rowsCount = manageOrderpage.getPagiationTotalRows(keyword);
		String[] num = rowsCount.split(" ");
		String totalRecords = num[4];

		if(Integer.parseInt(totalRecords) > 300 ) {
			String expectedMsg = "Export in progress Your requested export is in progress. We will send you an email when the export is available for download.";
			
			SeleniumFunction.click(manageOrderpage.clickExportOrdersBtn(keyword));
			String toastMessage = WaitTool.waitForElementPresentAndDisplay(driver, By.id("toast-container"), 50).getText();
			toastMessage = toastMessage.replaceAll("[\\t\\n\\r]+"," ");
			Assert.assertEquals(toastMessage, expectedMsg);
		} else {
			SeleniumFunction.click(manageOrderpage.clickExportOrdersBtn(keyword));
			WaitTool.sleep(10);

			String sheetName = JavaFunction.currentPSTDate("yyyy-M-d");
			int numOfRows = JavaFunction.countLineBufferedReader("download\\" + testData +  sheetName + ".csv");

			Log.info("Pagination count " + totalRecords + " \n Excel rows " + numOfRows);
			Assert.assertTrue(numOfRows >= Integer.parseInt(totalRecords));
		}
	}
}
