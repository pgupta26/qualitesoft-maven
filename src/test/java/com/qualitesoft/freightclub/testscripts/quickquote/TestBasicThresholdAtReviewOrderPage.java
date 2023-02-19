package test.java.com.qualitesoft.freightclub.testscripts.quickquote;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.appcommon.CommonOps;
import test.java.com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestBasicThresholdAtReviewOrderPage extends InitializeTest {
	
	@Test
	public void testBasicThresholdAtReviewOrderPage() {
		
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		if(i == 4 || i == 5 || i == 6 || i == 7) {
			Assert.assertTrue(quickQuote.verifyBasicThresholdAtReview());
			ScreenShot.takeScreenShot(driver, "Basic threshold present at review page");
		} else if(i == 2 || i == 3) {
			Assert.assertFalse(quickQuote.verifyBasicThresholdAtReview());
			ScreenShot.takeScreenShot(driver, "Basic threshold not present at review page");
		}
		
		commonOps.bookOrder(xr, i);
	}
}
