package test.java.com.qualitesoft.freightclub.testscripts.manageproducts;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.ScreenShot;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.UseAssert;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.pageobjects.ManageProducts;

public class TestDeleteProduct extends InitializeTest {
	
	@Test
	public void testDeleteProduct() {
		
		ManageProducts manageProducts = new ManageProducts(driver);
		
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int rowIndex=Integer.parseInt(Row);
		String productName=xr.getCellData("Input","ProductName", rowIndex).trim();
		
		//delete product
		SeleniumFunction.click(manageProducts.actions());
		SeleniumFunction.click(manageProducts.deleteSku());
		
		//verify delete product confirmation
		String expectedMsg = "Are you sure to delete product "+productName+"?";
		UseAssert.assertEquals(manageProducts.deleteProductConfirmation().getText(), expectedMsg);
		SeleniumFunction.click(manageProducts.deleteProductAccept());
		ScreenShot.takeScreenShot(driver, "Product deleted");
		
		//verify deleted product doesn't exist
		manageProducts.searchProductDataGrid(productName);
		Assert.assertTrue(manageProducts.noMatchingRecord().isDisplayed());
		
	}
}
