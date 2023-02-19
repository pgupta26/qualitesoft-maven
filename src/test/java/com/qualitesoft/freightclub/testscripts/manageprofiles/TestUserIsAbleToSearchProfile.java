package test.java.com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;
import test.java.com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestUserIsAbleToSearchProfile extends InitializeTest {

	@Test
	public void testUserIsAbleToSearchProfile() {
		QuickQuote quickQuote = new QuickQuote(driver);
		ProfileManagementPage profileManagement = new ProfileManagementPage(driver);

		//click on manage profile link
		SeleniumFunction.clickJS(driver, profileManagement.manageProfileLink());
		WaitTool.sleep(2);
		quickQuote.acceptPopup();
		WaitTool.sleep(2);

		//search profile
		SeleniumFunction.sendKeys(profileManagement.profileListfilter(), searchUser);
		profileManagement.profileListfilter().sendKeys(Keys.TAB);
		WaitTool.sleep(10);
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='User:']"),30));
		WaitTool.sleep(20);
	}
}
