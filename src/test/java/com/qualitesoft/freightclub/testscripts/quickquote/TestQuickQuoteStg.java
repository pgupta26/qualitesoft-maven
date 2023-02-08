package com.qualitesoft.freightclub.testscripts.quickquote;

import com.qualitesoft.core.Xls_Reader;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;

public class TestQuickQuoteStg extends InitializeTest {
	
	@Test
	public void testGetQuote() {

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		//clear existing data
		xr.setCellData("Input","OrderId", i,"");

		//Read data from sheet for selected row
		String shipmentType=xr.getCellData("Input","shipmentType", i).trim();
		String serviceLevel=xr.getCellData("Input","serviceLevel", i).trim();
		String orderReferenceID=xr.getCellData("Input","orderReferenceID", i).trim();
		String pickUpZip=xr.getCellData("Input","pickUpZip", i).trim();
		String dropOffZip=xr.getCellData("Input","dropOffZip", i).trim();
		String pickUpType=xr.getCellData("Input","pickUpType", i).trim();
		String dropOffType=xr.getCellData("Input","dropOffType", i).trim();			
		String packageType = xr.getCellData("Input", "packageType", i).trim();
		String Weight=xr.getCellData("Input","Weight", i).trim();
		String DimensionL=xr.getCellData("Input","DimensionL", i).trim();
		String DimensionW=xr.getCellData("Input","DimensionW", i).trim();
		String DimensionH=xr.getCellData("Input","DimensionH", i).trim();
		String DeclaredValue=xr.getCellData("Input","DeclaredValue", i).trim();
		String carrier = xr.getCellData("Input","Carrier", i).trim();
		String quantity=xr.getCellData("Input","Quantity", i).trim();
		String category=xr.getCellData("Input","category", i).trim();			
		String insurance=xr.getCellData("Input","UpsInsurance", i).trim();			

		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		
		SeleniumFunction.clickJS(driver, quickQuote.quickQuoteLink());
		WaitTool.sleep(20);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		SeleniumFunction.scrollUpByPixel(driver, "700");
		WaitTool.sleep(2);
		SeleniumFunction.clickJS(driver, quickQuote.shipmentType(shipmentType));
		WaitTool.sleep(2);
		SeleniumFunction.click(quickQuote.OrderDate());
		SeleniumFunction.sendKeys(quickQuote.OrderReferenceID(), orderReferenceID);
		SeleniumFunction.click(quickQuote.serviceLevel(serviceLevel));	
		
		SeleniumFunction.sendKeys(quickQuote.PickUpZip(), pickUpZip.trim());
		SeleniumFunction.sendKeys(quickQuote.DropOffZip(),dropOffZip.trim());

		SeleniumFunction.selectByVisibleText(quickQuote.PickUpLocationType(), pickUpType);
		SeleniumFunction.selectByVisibleText(quickQuote.DropOffLocationType(), dropOffType);
		
		//uncheck insurance checkbox
		if(insurance.equals("No"))
			quickQuote.deselectInsurance();

		if(category.equalsIgnoreCase("Other")){
			SeleniumFunction.selectByvalue(quickQuote.Category(1), "347");
			SeleniumFunction.click(quickQuote.popupCateogory(1));
			WaitTool.sleep(2);
		}
		else {
			SeleniumFunction.selectByvalue(quickQuote.Category(1), "346");
		}

		SeleniumFunction.scrollDownUptoFooter(driver);
		WaitTool.sleep(2);
		SeleniumFunction.sendKeys(quickQuote.quantity(1), quantity);
		SeleniumFunction.clickJS(driver, quickQuote.PackageType(packageType, 1));
		SeleniumFunction.sendKeys(quickQuote.Weight(1), Weight);
		SeleniumFunction.sendKeys(quickQuote.DimensionL(1), DimensionL);
		SeleniumFunction.sendKeys(quickQuote.DimensionW(1), DimensionW);
		SeleniumFunction.sendKeys(quickQuote.DimensionH(1), DimensionH);
		SeleniumFunction.sendKeys(quickQuote.DeclaredValue(1), DeclaredValue);
		
		WaitTool.sleep(2);
		ScreenShot.takeScreenShot(driver, "Shipmentinfo "+shipmentType+" "+packageType);
		
		//move to carriers page
		commonOps.selectCarrier();
				
		String insuranceValue = null;
		if(insurance.equals("Yes")) {
			insuranceValue = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/44.png']/ancestor::tr/td[5]/ul/li"), 10).getText();
			Log.info("Insurance Value: "+insuranceValue);
			insuranceValue = insuranceValue.split("\\(")[1].split("\\)")[0];
		}
		String amount = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/44.png']/ancestor::tr/td[6]/span"), 10).getText().trim();
		quickQuote.selectCarrier(carrier);
		
		//shipment completion information
		String description=xr.getCellData("ShipmentInformation","Description", 2).trim();
		String specialHandlingInstructions=xr.getCellData("ShipmentInformation","SpecialHandlingInstructions", 2).trim();
		String pickUpAddressLine1=xr.getCellData("ShipmentInformation","PickUpAddressLine1", 2).trim();
		String pickUpFirstName=xr.getCellData("ShipmentInformation","PickUpFirstName", 2).trim();
		String pickUpLastName=xr.getCellData("ShipmentInformation","PickUpLastName", 2).trim();
		String pickUpPhone1=xr.getCellData("ShipmentInformation","PickUpPhone1", 2).trim();
		String dropOffAddressLine1=xr.getCellData("ShipmentInformation","DropOffAddressLine1", 2).trim();
		String dropOffFirstName=xr.getCellData("ShipmentInformation","DropOffFirstname", 2).trim();
		String dropOffLastName=xr.getCellData("ShipmentInformation","DropOffLastName", 2).trim();
		String dropOffPhone1=xr.getCellData("ShipmentInformation","DropPhone", 2).trim();
		
		if(packageType.equals("Standard Pallet 1") || packageType.equals("Custom Pallet (enter dimensions)")) {
			SeleniumFunction.scrollUpByPixel(driver, "300");
			SeleniumFunction.click(quickQuote.genericPallet());
			SeleniumFunction.sendKeys(quickQuote.palletDescription(), description);
		} else {
			SeleniumFunction.sendKeys(quickQuote.PalletDesc(), description);
			SeleniumFunction.sendKeys(quickQuote.SpecialHandling(), specialHandlingInstructions);
		}

		//pick up location detail
		String pickLocName = "PickUp"+JavaFunction.getRandomNumber(1, 10000);
		SeleniumFunction.sendKeys(quickQuote.LocationName(), pickLocName);
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[contains(text(),'Add location with name')]"), 10));	
		SeleniumFunction.sendKeys(quickQuote.pickUpAddress1(), pickUpAddressLine1);
		SeleniumFunction.sendKeys(quickQuote.pickUpFirstName(), pickUpFirstName);
		SeleniumFunction.sendKeys(quickQuote.pickUpLastName(), pickUpLastName);
		SeleniumFunction.sendKeys(quickQuote.pickUpPhone1(), pickUpPhone1);

		//drop off location detail
		String dropLocName = "DropOff"+JavaFunction.getRandomNumber(1, 10000);
		SeleniumFunction.scrollUpByPixel(driver, "250");
		SeleniumFunction.sendKeys(quickQuote.LocationName(), dropLocName);
		try {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='"+dropLocName+"']"), 10));	
		} catch(Exception ex) {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[contains(text(),'Add location with name')])[2]"), 10));	
		}
		SeleniumFunction.sendKeys(quickQuote.DropAddress1(), dropOffAddressLine1);
		SeleniumFunction.sendKeys(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='col-xs-12 col-md-6'][2]/descendant::input[@id='Phone1']"), 30), dropOffPhone1);
		SeleniumFunction.sendKeys(quickQuote.DropFirstName(), dropOffFirstName);
		SeleniumFunction.sendKeys(quickQuote.DropLastName(), dropOffLastName);
		SeleniumFunction.sendKeys(quickQuote.DropPhone1(), dropOffPhone1);
		
		
		//Review and Book Tab
		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.click(quickQuote.ReviewOrder());
		WaitTool.sleep(10);
		SeleniumFunction.clickJS(driver, quickQuote.Book());
		WaitTool.sleep(10);
		
		if(quickQuote.acknowleadgeBtnStatus() == true){
			String actualPopupHeader = SeleniumFunction.getText(quickQuote.acknowleadgeModalHeader());
			String actualPopupBody = SeleniumFunction.getText(quickQuote.acknowleadgeModalBody());
			UseAssert.assertEquals(actualPopupHeader, "A matching order has already been placed.");
			UseAssert.assertEquals(actualPopupBody, "We have identified that the order that you are about to place is similar to an order that has already been booked. Would you like to continue with placing a duplicate order?");
			SeleniumFunction.click(quickQuote.acknowleadgeBtn());
			SeleniumFunction.clickJS(driver, quickQuote.Book());
		}
		
		ScreenShot.takeScreenShot(driver, "Order Confirmation "+shipmentType+" "+packageType);
		SeleniumFunction.click(quickQuote.Okbutton1());
		WaitTool.sleep(20);
		
		ManageOverages manageOverage = new ManageOverages(driver);

		String orderID = manageOverage.gridData(1, 1).getText();
		Log.info("Order ID: "+orderID);
		String orderDate = manageOverage.gridData(1, 3).getText().replace("/", "-");
		Log.info("Order Date: "+orderDate);
		String wayBill = manageOverage.gridData(1, 8).getText();
		Log.info("WayBill: "+wayBill);
		String tracking = manageOverage.gridData(1, 9).getText();
		Log.info("Tracking: "+tracking);
		
		//set order id in excel
		xr.setCellData("Input","OrderId", i, orderID);
		xr.setCellData("Input","pickUpDate", i, orderDate.replace("/", "-"));
		xr.setCellData("Input","Amount", i, amount);
		xr.setCellData("Input","Tracking#", i, tracking);
		xr.setCellData("Input","WayBill", i, wayBill);
		xr.setCellData("Input","Insurance", i, insuranceValue);
		xr.setCellData("ClaimDetail","ClaimStatus", i, "Initiated");
		xr.setCellData("ShipmentInformation","PickCompanyName", 2, pickLocName);
		xr.setCellData("ShipmentInformation","DropCompanyName", 2, dropLocName);
		
		//update test data for manage invoices
		Xls_Reader manageInvoiceTestData = new Xls_Reader("testdata/FCfiles/"+ env +"/Overages/ManageInvoiceTestData.xlsx");
		manageInvoiceTestData.setCellData("Sheet1", "OrderDate", 2, orderDate);
		manageInvoiceTestData.setCellData("Sheet1", "Customer PO #", 2, orderReferenceID);
		manageInvoiceTestData.setCellData("Sheet1", "OverageID", 2, orderID);

		Xls_Reader secondaryInvoice = new Xls_Reader("testdata/FCfiles/"+ env +"/Overages/SecondaryInvoiceTemplate.xlsx");
		secondaryInvoice.setCellData("Sec invoice Master", "FC Order ID", 2, orderID);
		secondaryInvoice.setCellData("Sec invoice Master", "Tracking #", 2, tracking);
		secondaryInvoice.setCellData("Sec invoice Master", "SECONDARY INV #", 2, orderID+"+1");
		
		//random new invoice amount generation
		boolean flag=true;
		String newInvoiceAmount = null;
		int lastDigit;
		
		while(flag) {
			 newInvoiceAmount = String.valueOf(JavaFunction.getRandomNumber(1, 999))+"."+String.valueOf(JavaFunction.getRandomNumber(1, 99));
			 lastDigit = Integer.parseInt(String.valueOf(newInvoiceAmount.charAt(newInvoiceAmount.length()-1)));
			 if(lastDigit > 0) {
				flag = false; 
			 }
		}
		Log.info("New Invoice Amount Generated:  "+newInvoiceAmount);
		secondaryInvoice.setCellData("Sec invoice Master", "New Invoice Amount", 2, newInvoiceAmount);
		
		//update test data for manage claims carrier payment
		Xls_Reader manageClaimsTestData;
		if(i == 2) {
			
			manageClaimsTestData = new Xls_Reader("testdata/FCfiles/"+ env +"/ManageClaims/Carrier_Payment_Template.xlsx");
			manageClaimsTestData.setCellData("Bulk Payment Update", "Claim ID", 2, orderID);
			manageClaimsTestData.setCellData("Bulk Payment Update", "Payment Date", 2, JavaFunction.currentDateUSFormat().replaceAll("-", "/"));
		
		} else {
			
			//update test data for manage claims carrier payment
			manageClaimsTestData = new Xls_Reader("testdata/FCfiles/"+ env +"/ManageClaims/Company_Payment_Template.xlsx");
			manageClaimsTestData.setCellData("Bulk Payment Update", "Claim ID", 2, orderID);
		
		}
		
		WaitTool.sleep(5);
	}
}
