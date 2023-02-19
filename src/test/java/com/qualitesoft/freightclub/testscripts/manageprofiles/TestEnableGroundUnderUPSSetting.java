package test.java.com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;

public class TestEnableGroundUnderUPSSetting extends InitializeTest {
	
	@Test
	public void testEnableGroudUnderUPSSetting() {
		try {
			ProfileManagementPage profile=new ProfileManagementPage(driver);

			SeleniumFunction.scrollUPUptoHeader(driver);
			WaitTool.sleep(5);
			//click UPS setting
			SeleniumFunction.clickJS(driver, profile.upsSetting());	
			
			//click ground checkbox
			if(!profile.selectGround().isSelected()) {
				SeleniumFunction.click(profile.selectGround());
			}
			
			//click UPS SurePost checkbox
			WaitTool.sleep(3);
			if(!profile.selectUPSSurepost().isSelected()) {
				SeleniumFunction.click(profile.selectUPSSurepost());	
			}
			
			//click on save button
			SeleniumFunction.scrollDownByPixel(driver, "1000");
			SeleniumFunction.click(profile.save());
			WaitTool.sleep(10);
			ScreenShot.takeScreenShot(driver, "Ground and SurePost selected successfully.");
			
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}	
	}

}
