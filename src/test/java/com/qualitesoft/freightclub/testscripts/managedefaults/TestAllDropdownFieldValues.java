package com.qualitesoft.freightclub.testscripts.managedefaults;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import test.java.com.qualitesoft.core.InitializeTest;
import models.managedefaults.DefaultsDropdownOptions;
import com.qualitesoft.freightclub.pageobjects.ManageDefaultsObjects;
import com.qualitesoft.freightclub.testscripts.World;

public class TestAllDropdownFieldValues extends InitializeTest {

    @Test
    public void testDefaultFieldValuesAtManageDefaultsScreen() throws FileNotFoundException {

        BufferedReader br = new BufferedReader(new FileReader("./jsondata/"+dataFile));
        Gson gson = new Gson();
        DefaultsDropdownOptions dropdownOptions = gson.fromJson(br, DefaultsDropdownOptions.class);

        ManageDefaultsObjects manageDefaultsObj = new ManageDefaultsObjects(driver);

        manageDefaultsObj.clickManageDefaults();


        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default LTL Service Level"), dropdownOptions.getLTLServiceLevelOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default Pick Up Location Type"), dropdownOptions.getPickupLocationTypeOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default Parcel Pick Up Location"), dropdownOptions.getParcelPickupLocationOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default LTL Pick Up Location"), dropdownOptions.getLTLPickupLocationOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default Drop Off Location Type"), dropdownOptions.getDrop_offLocationTypeOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default Drop Off Location"), dropdownOptions.getDrop_offLocationOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default Pick Up Window"), dropdownOptions.getPickupWindowOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default Landing Page"), dropdownOptions.getDefaultLandingPage());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Default Label File Name"), dropdownOptions.getLabelFileNameOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Preferred Tracking View"), dropdownOptions.getPreferredTrackingViewOptions());

        Assert.assertEquals(manageDefaultsObj.getDropdownOptionsText("Contact Phone Number"), dropdownOptions.getContactPhoneNumberTypeOptions());

        World.setDropdownOptions(dropdownOptions);


    }

}