package test.java.com.qualitesoft.freightclub.testscripts.manageorders;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import main.java.models.UpdateOrderInformation;
import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.UseAssert;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import test.java.com.qualitesoft.freightclub.pageobjects.OrderDetailPage;
import test.java.com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;
import test.java.com.qualitesoft.freightclub.pageobjects.UpdateTrackingStatusPage;

public class TestVerifyUserIsAbleToUpdateOrderInformation extends InitializeTest {

	@Test
	public void testVerifyUserIsAbleToUpdateOrderInformation() {

		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		OrderDetailPage	orderDetailsPage= new OrderDetailPage(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		SeleniumFunction.clickJS(driver, manageOrderpage.manageOrdersLink());
		quickQuote.acceptPopup();

		String orderid=xr.getCellData("Input","OrderId", i).trim();
		
		SeleniumFunction.sendKeys(manageOrderpage.searchFields("1"), orderid);
		manageOrderpage.searchFields("1").sendKeys(Keys.ENTER);
		WaitTool.sleep(10);

		SeleniumFunction.click(manageOrderpage.ViewDetail());
		WaitTool.sleep(5);

		try {
			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(3);
			quickQuote.acceptPopup();
			quickQuote.acceptPopup();
			
			SeleniumFunction.click(orderDetailsPage.adminTab());
			SeleniumFunction.scrollDownByPixel(driver, "150");

			SeleniumFunction.click(orderDetailsPage.editOrder());
			try {
				
				SeleniumFunction.getCurrentWindow(driver);
				WaitTool.sleep(3);

				UpdateTrackingStatusPage updateTrackingStatusPage = new UpdateTrackingStatusPage(driver);
				
				//update order information
				SeleniumFunction.sendKeys(updateTrackingStatusPage.getField("Customer PO Number:"), UpdateOrderInformation.customerPONumber);
				SeleniumFunction.sendKeys(updateTrackingStatusPage.getField("Quoted Price"), UpdateOrderInformation.quotedPrice);
				SeleniumFunction.sendKeys(updateTrackingStatusPage.getField("Address Line 1"), UpdateOrderInformation.pickUpAddressLine1);
				SeleniumFunction.sendKeys(updateTrackingStatusPage.getField("Address Line 2"), UpdateOrderInformation.pickUpAddressLine2);
				SeleniumFunction.sendKeys(updateTrackingStatusPage.getField("Zip/Postal Code"), UpdateOrderInformation.pickUpZipPostalCode);
				
				SeleniumFunction.sendKeys(updateTrackingStatusPage.getDropOffFields("Address Line 1"), UpdateOrderInformation.dropOffAddressLine1);
				SeleniumFunction.sendKeys(updateTrackingStatusPage.getDropOffFields("Address Line 2"), UpdateOrderInformation.dropOffAddressLine2);
				SeleniumFunction.sendKeys(updateTrackingStatusPage.getDropOffFields("Zip/Postal Code"), UpdateOrderInformation.dropOffZipPostalCode);
				updateTrackingStatusPage.getDropOffFields("Zip/Postal Code").sendKeys(Keys.TAB);
				WaitTool.sleep(5);
				SeleniumFunction.clickJS(driver, updateTrackingStatusPage.saveOrder());
				
				//close window
				WaitTool.sleep(15);
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				
				//refresh order details page
				SeleniumFunction.refreshCurrentWindow(driver);
				
				//validation updated order information
				UseAssert.assertEquals(orderDetailsPage.getLabel("Customer PO #:", 1).getText(), UpdateOrderInformation.customerPONumber);
				UseAssert.assertEquals(orderDetailsPage.getLabel("Quoted Amount:", 1).getText(), "$"+UpdateOrderInformation.quotedPrice);
				System.out.println(orderDetailsPage.pickUpAddress().getText().replaceAll("[\n,\\s]+$", ""));
				Assert.assertTrue(orderDetailsPage.pickUpAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.pickUpAddressLine1));
				Assert.assertTrue(orderDetailsPage.pickUpAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.pickUpAddressLine2));
				Assert.assertTrue(orderDetailsPage.pickUpAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.pickUpZipPostalCode));
				Assert.assertTrue(orderDetailsPage.dropOffAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.dropOffAddressLine1));
				Assert.assertTrue(orderDetailsPage.dropOffAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.dropOffAddressLine2));
				Assert.assertTrue(orderDetailsPage.dropOffAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.dropOffZipPostalCode));

				
			}catch(Exception ex) {
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				throw ex;
			}
			
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
		}catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			throw ex;
		}
		
		//refresh order details page
		SeleniumFunction.refreshCurrentWindow(driver);
				
		SeleniumFunction.sendKeys(manageOrderpage.searchFields("1"), orderid);
		manageOrderpage.searchFields("1").sendKeys(Keys.ENTER);
		WaitTool.sleep(10);
		
		UseAssert.assertEquals(manageOrderpage.getColumnData("3").trim(), UpdateOrderInformation.customerPONumber);
		UseAssert.assertEquals(manageOrderpage.getColumnData("11").trim(), "$"+UpdateOrderInformation.quotedPrice);
		
	}

}
