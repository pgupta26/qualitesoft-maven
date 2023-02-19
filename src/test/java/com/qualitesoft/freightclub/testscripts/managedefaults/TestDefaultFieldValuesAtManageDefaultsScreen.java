package test.java.com.qualitesoft.freightclub.testscripts.managedefaults;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import main.java.models.managedefaults.ManageDefaults;
import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.freightclub.pageobjects.ManageDefaultsObjects;
import test.java.com.qualitesoft.freightclub.testscripts.World;

public class TestDefaultFieldValuesAtManageDefaultsScreen extends InitializeTest {
	
	@Test
	public void testDefaultFieldValuesAtManageDefaultsScreen() throws FileNotFoundException {
		
		BufferedReader br = new BufferedReader(new FileReader("./jsondata/"+dataFile));
		Gson gson = new Gson();
		ManageDefaults manageDefaults = gson.fromJson(br, ManageDefaults.class);
		
		ManageDefaultsObjects manageDefaultsObj = new ManageDefaultsObjects(driver);
		
		manageDefaultsObj.clickManageDefaults();
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default LTL Service Level"), manageDefaults.getDefaultLTLServiceLevel());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default Pick Up Location Type"), manageDefaults.getDefaultPickupLocationType());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default Parcel Pick Up Location"), manageDefaults.getDefaultParcelPickupLocation());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default LTL Pick Up Location"), manageDefaults.getDefaultLTLPickupLocation());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default Drop Off Location Type"), manageDefaults.getDefaultDropoffLocationType());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default Drop Off Location"), manageDefaults.getDefaultDropoffLocation());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default Pick Up Window"), manageDefaults.getDefaultPickupWindow());
		
		Assert.assertEquals(manageDefaultsObj.getDefaultSpecialInstructions(), manageDefaults.getDefaultSpecialInstructions());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default Landing Page"), manageDefaults.getDefaultLandingPage());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Default Label File Name"), manageDefaults.getDefaultLabelFileName());
		
		Assert.assertEquals(manageDefaultsObj.isDontShowTheServiceLevelCheckboxSelected(), manageDefaults.isDontShowServiceLabelCheckbox());

		Assert.assertEquals(manageDefaultsObj.isIncludeCustomerPOBarcodeOnBillOfLadingCheckboxSelected(), manageDefaults.isIncludeCustomerPOCheckbox());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Preferred Tracking View"), manageDefaults.getPreferredTrackingView());
		
		Assert.assertEquals(manageDefaultsObj.getSelectDrpdownFieldValue("Contact Phone Number"), manageDefaults.getContactPhoneNumberType());
		
		Assert.assertEquals(manageDefaultsObj.isContactPhoneNumberEnabled(), manageDefaults.getContactPhoneNumberType());
		
		World.setManageDefaults(manageDefaults);
		
			
	}

}