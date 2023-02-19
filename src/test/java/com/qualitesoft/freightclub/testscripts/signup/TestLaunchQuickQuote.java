package test.java.com.qualitesoft.freightclub.testscripts.signup;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.WaitTool;


public class TestLaunchQuickQuote extends InitializeTest {
	@Test
	public void testLaunchQuickQuote(){
		try {
			driver.get("https://qa.freightclub.com/Home/QuickQuote#/shipmentInformation");
			WaitTool.sleep(5);
			ScreenShot.takeScreenShot(driver, "Quick Quote page");	
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
}

