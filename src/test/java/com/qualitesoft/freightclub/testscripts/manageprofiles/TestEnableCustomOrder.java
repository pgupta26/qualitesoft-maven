package test.java.com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;

public class TestEnableCustomOrder extends InitializeTest {

	@Test
	public void testEnableCustomOrder() {
		ProfileManagementPage profileManagementPage = new ProfileManagementPage(driver);
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,3500)");
		profileManagementPage.enableTruckLoad(enableOption);
		ScreenShot.takeScreenShot(driver, "Custom Order Button Enabled");
		WaitTool.sleep(5);
		SeleniumFunction.clickJS(driver,profileManagementPage.saveProfileButtonSurePost());
		WaitTool.sleep(5);
	}
}
