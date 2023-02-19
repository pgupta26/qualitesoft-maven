package test.java.com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.Log;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;

public class TestSelectPaymentMethod extends InitializeTest {

	@Test
	public void testSelectPaymentMethod() {
		ProfileManagementPage profileManagement = new ProfileManagementPage(driver);

		Xls_Reader xr = new Xls_Reader(testData);
		int rowIndex = Integer.parseInt(Row);
		Log.info("Row Number: "+rowIndex);	

		String paymentMethod = xr.getCellData("ContactInfo", "PaymentMethod", rowIndex);

		SeleniumFunction.moveToElement(driver, profileManagement.paymentType());
		SeleniumFunction.selectByVisibleText(profileManagement.paymentType(), paymentMethod);
		ScreenShot.takeScreenShot(driver, "payment method"+paymentMethod);		

		//click on save profile
		SeleniumFunction.scrollDownUptoFooter(driver);
		WaitTool.sleep(5);
		SeleniumFunction.clickJS(driver, profileManagement.saveProfileButtonSurePost());
		ScreenShot.takeScreenShot(driver, "profile saved successfully");
		WaitTool.sleep(20);
	}
}
