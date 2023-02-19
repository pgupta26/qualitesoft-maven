package test.java.com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.Log;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;

public class TestSelectAccountType extends InitializeTest {
	
	@Test
	public void testSelectAccountType() {
		
		ProfileManagementPage profileManagement = new ProfileManagementPage(driver);

		Xls_Reader xr = new Xls_Reader(testData);
		int rowIndex = Integer.parseInt(Row);
		Log.info("Row Number: "+rowIndex);	

		String accountType = xr.getCellData("ContactInfo", "AccountType", rowIndex);

		SeleniumFunction.moveToElement(driver, profileManagement.accountTypeDropdown());
		SeleniumFunction.selectByVisibleText(profileManagement.accountTypeDropdown(), accountType);
		ScreenShot.takeScreenShot(driver, "Account type "+accountType+" selected");		

		//click on save profile
		SeleniumFunction.scrollDownUptoFooter(driver);
		WaitTool.sleep(5);
		SeleniumFunction.clickJS(driver, profileManagement.saveProfileButtonSurePost());
		ScreenShot.takeScreenShot(driver, "Account type "+accountType+" saved");
		WaitTool.sleep(20);
		
	}
}
