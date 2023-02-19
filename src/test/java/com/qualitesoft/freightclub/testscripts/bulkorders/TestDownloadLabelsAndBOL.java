package test.java.com.qualitesoft.freightclub.testscripts.bulkorders;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.freightclub.pageobjects.BulkOrdersPage;

public class TestDownloadLabelsAndBOL extends InitializeTest {
	
	@Test
	public void testDownloadLabelsAndBOL() {
		
		BulkOrdersPage bulkOrdersPage = new BulkOrdersPage(driver);
		
		//click download labels button
		SeleniumFunction.click(bulkOrdersPage.downloadLabelsButton(1));
		
		//click download BOL button
		SeleniumFunction.click(bulkOrdersPage.downloadBOLButton(1));
		
		//verify downloaded files
		ScreenShot.takeScreenShot(driver, "Labels and BOLs downloaded");
		
	}
}
