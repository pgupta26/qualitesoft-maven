package test.java.com.qualitesoft.freightclub.testscripts.customorders;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestCustomPopupNotPresent extends InitializeTest {

	@Test
	public void testCustomPopupNotPresent() {
		WaitTool.sleep(20);
		Assert.assertTrue(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//span[text()='Choose Your Rate']")));
		ScreenShot.takeScreenShot(driver, "Custom Order Popup not Present");

		//Back to Previous Page
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		WaitTool.sleep(2);
		quickQuote.acceptPopup();
		WaitTool.sleep(2);
		SeleniumFunction.scrollDownByPixel(driver, "5000");
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/article/div/div[3]/div/div[5]/div/button"), 30));
		WaitTool.sleep(2);
	}
}
