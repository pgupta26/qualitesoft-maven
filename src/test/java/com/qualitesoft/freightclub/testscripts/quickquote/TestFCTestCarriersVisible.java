package test.java.com.qualitesoft.freightclub.testscripts.quickquote;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestFCTestCarriersVisible extends InitializeTest {
	
	@Test
	public void testFCTestCarriersVisible() {
			QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
			
			quickQuote.waitForQuotesToAppear();
			SeleniumFunction.scrollDownUptoFooter(driver);
			quickQuote.expandCarries();
			WaitTool.sleep(5);
			quickQuote.acceptPopup();
			Assert.assertTrue(quickQuote.verifyCarrier(carrierName));
			ScreenShot.takeFullScreenShot("FC Test Carrier Present");
	}
}
