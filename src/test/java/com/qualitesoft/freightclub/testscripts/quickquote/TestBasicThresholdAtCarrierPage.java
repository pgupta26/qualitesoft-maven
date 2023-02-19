package test.java.com.qualitesoft.freightclub.testscripts.quickquote;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.Log;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestBasicThresholdAtCarrierPage extends InitializeTest {

	@Test
	public void testBasicThresholdAtCarrierPage() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		
		quickQuote.waitForQuotesToAppear();
		SeleniumFunction.scrollDownUptoFooter(driver);
		quickQuote.expandCarries();
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		Assert.assertTrue(quickQuote.verifyCarrier(carrierName));
		ScreenShot.takeScreenShot(driver, carrierName+ "verified");

		int i=Integer.parseInt(Row);
		if(i == 4 || i == 5) {
			Log.info("Is Carrier Present: "+quickQuote.verifyBasicThreshold(carrierName));
			Assert.assertTrue(quickQuote.verifyBasicThreshold(carrierName));
			ScreenShot.takeScreenShot(driver, "Basic threshold pressent for "+carrierName);
		} else if(i == 2 || i == 3) {
			Assert.assertFalse(quickQuote.verifyBasicThreshold(carrierName));
			ScreenShot.takeScreenShot(driver, "Basic threshold not present for "+carrierName);
		} else {
			Log.info("Do nothing");
		}
		
		quickQuote.selectCarrier(carrierName);
	}
}
