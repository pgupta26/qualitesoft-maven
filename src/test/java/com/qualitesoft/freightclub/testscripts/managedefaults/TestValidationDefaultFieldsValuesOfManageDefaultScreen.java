package test.java.com.qualitesoft.freightclub.testscripts.managedefaults;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.freightclub.pageobjects.ManageDefaultsObjects;

public class TestValidationDefaultFieldsValuesOfManageDefaultScreen extends InitializeTest {
	
	@Test
	public void testValidationDefaultFieldsValuesOfManageDefaultScreen() {
			
		ManageDefaultsObjects manageDefaultsObj = new ManageDefaultsObjects(driver);
		manageDefaultsObj.verifyManageDefaultsFields(testData, Row);
		
		
	}

}
