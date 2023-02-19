package test.java.com.qualitesoft.freightclub.testscripts.managecarriers;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.ManageCarrierPage;
import test.java.com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestDisableLooseCartoonAccepted extends InitializeTest {

	@Test
	public void testDisableLooseCartoonAccepted() {
		String[] carrierNames=carrierName.split(",");
		ManageCarrierPage manageCarrierPage = new ManageCarrierPage(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		manageCarrierPage.manageCarriersLink();
		SeleniumFunction.click(manageCarrierPage.classicView());
		quickQuote.acceptPopup();
		for(int carriersCount=0; carriersCount<carrierNames.length; carriersCount++) {
			WaitTool.sleep(5);
			manageCarrierPage.filterCarrier(carrierNames[carriersCount]);
			WaitTool.sleep(5);
			SeleniumFunction.scrollDownByPixel(driver, "1300");
			WaitTool.sleep(5);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Options"), 10));
			manageCarrierPage.unselectLooseCartoon();
			WaitTool.sleep(5);
			SeleniumFunction.scrollDownByPixel(driver, "500");
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Update']"), 10));
			WaitTool.sleep(2);
			ScreenShot.takeScreenShot(driver, "Disable Loose Cartoon");
		}
	}
}
