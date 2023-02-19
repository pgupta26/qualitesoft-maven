package test.java.com.qualitesoft.freightclub.testscripts;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.Log;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.HomePage;

public class TestLogOff extends InitializeTest{
	
	@Test
	public void testlogoff(){
		WaitTool.sleep(5);
		HomePage homePage = new HomePage(driver);
		try {
			SeleniumFunction.click(homePage.logoff());
		}catch(Exception ex) {
			try {
				Log.info("Log Off Retry...............");
				InitializeTest.getDriver().navigate().refresh();
				WaitTool.sleep(5);
				SeleniumFunction.click(homePage.logoff());
			}catch(Exception e) {
				throw e;
			}
		}
		WaitTool.sleep(10);
		String title = driver.getTitle();
		
		if(!title.equals("Log in - Freight Club"))
			SeleniumFunction.clickJS(driver, homePage.logoff());
		ScreenShot.takeScreenShot(driver, "Logout");		
	}
}
