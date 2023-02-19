package test.java.com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import main.java.models.managedefaults.ManageDefaults;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.appcommon.CommonOps;
import test.java.com.qualitesoft.freightclub.testscripts.World;

public class ManageDefaultsObjects {

	WebDriver driver;

	public ManageDefaultsObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void clickManageDefaults() {
		
			SeleniumFunction
					.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/Profile/ManageDefaults']"), 10));			
	}
	
	public String getSelectDrpdownFieldValue(String fieldName) {

		WaitTool.sleep(1);
		WebElement element = driver.findElement(By.xpath("//label[text()='"+fieldName+"']/following-sibling::select"));

		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("arguments[0].style.display='block';", element);

		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+fieldName+"']/following-sibling::select/option"), 10)); 
	}
	
	public void setSelectDropdownFieldValue(String fieldName, String value) {

		try {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+fieldName+"']/following-sibling::div[contains(@class,'form-input single')]/div"), 30));
			WaitTool.sleep(1);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+fieldName+"']/following-sibling::div[contains(@class,'form-input single')]/descendant::*[contains(text(),'"+value+"')]"), 30));
		}catch(Exception ex) {
			System.out.println("dfjsdkfjsdfl");
		}
		
	}
	
	public void setDefaultSpecialInstructions(String value) {
		SeleniumFunction.sendKeys(WaitTool.waitForElementPresentAndDisplay(driver, By.id("defaultSpecialInstructions"), 10), value);
	}
	
	public String getDefaultSpecialInstructions() {
		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("defaultSpecialInstructions"), 10).getAttribute("value");
	}
	
	public void enableDontShowTheServiceLevelCheckbox() {
		
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//b[text()='Basic Threshold (No Signature)']/parent::span/input"), 10));
	}
	
	public boolean isDontShowTheServiceLevelCheckboxSelected() {
		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//b[text()='Basic Threshold (No Signature)']/parent::span/input"), 10).isSelected();
	}
	
	public void enableIncludeCustomerPOBarcodeOnBillOfLadingCheckbox() {
		
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Include Customer PO# Barcode on Bill of Lading.')]/input"), 10));
	}
	
	public boolean isIncludeCustomerPOBarcodeOnBillOfLadingCheckboxSelected() {
		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Include Customer PO# Barcode on Bill of Lading.')]/input"), 10).isSelected(); 
	}
	
	public void setContactPhoneNumber(String phoneNumber) {
		
		SeleniumFunction.sendKeys(WaitTool.waitForElementPresentAndDisplay(driver, By.id("trackingPhoneNumber"), 10), phoneNumber);
	}
	
	public boolean isContactPhoneNumberEnabled() {
		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("trackingPhoneNumber"), 10).isEnabled();
	}
	
	public void clickSaveInfoButton() {
		
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[@class='btn btn-primary pull-right'])[2]"), 10));
	}
	
	public void verifyManageDefaultsFields(String testData, String row) {

		ManageDefaults manageDefaults = World.getManageDefaults();
		
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		//click on quick quote
		SeleniumFunction.clickJS(driver, quickQuote.quickQuoteLink());

		//select LTL shipment type
		SeleniumFunction.clickJS(driver, quickQuote.shipmentType("Less Than Truckload"));

		//verify service level
		Assert.assertEquals(this.getSelectDrpdownFieldValue("Service Level"), manageDefaults.getDefaultLTLServiceLevel());

		//verify LTL pick up location
		Assert.assertEquals(quickQuote.getPickUpLocation(), manageDefaults.getDefaultParcelPickupLocation());

		//verify pick up location type
		Assert.assertEquals(quickQuote.getPickUpLocationType(), manageDefaults.getDefaultPickupLocationType());

		//verify drop off location
		Assert.assertEquals(quickQuote.getDropOffLocation(), manageDefaults.getDefaultDropoffLocation());

		//verify drop off location type
		Assert.assertEquals(quickQuote.getDropOffLocationType(), manageDefaults.getDefaultDropoffLocationType());

		//select parcel shipment type
		SeleniumFunction.clickJS(driver, quickQuote.shipmentType("Parcel"));

		//verify parcel pick up location
		Assert.assertEquals(quickQuote.getPickUpLocation(), manageDefaults.getDefaultParcelPickupLocation());

		//enter quick quote details
		CommonOps commonOps = new CommonOps();

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(row);
		
		String carrier = xr.getCellData("Input","Carrier", i).trim();
		String packageType = xr.getCellData("Input","packageType", i).trim();
		
		//enter shipment information
		commonOps.shipmentInformation(xr,i);

		//add item package type information
		commonOps.itemInformation(xr, i, 1, packageType);

		//select carrier
		commonOps.selectCarrier();

		//verify basic threshold visible
		Assert.assertTrue(quickQuote.verifyBasicThreshold(""));
		quickQuote.selectCarrier(carrier);

		//verify special instructions 
		Assert.assertEquals(quickQuote.SpecialHandling().getText(), manageDefaults.getDefaultSpecialInstructions());

		//enter shipment information
		commonOps.shipmentCompletion(xr, i);

		SeleniumFunction.click(quickQuote.ReviewOrder());
		quickQuote.acceptPopup();
		WaitTool.sleep(10);

		commonOps.bookOrder(xr, i);

	}
}
