package test.java.com.qualitesoft.freightclub.testscripts.manageorders;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.appcommon.CommonOps;

public class TestOpenManageOrdersAndSearchOrderId extends InitializeTest {
	
	@Test
	public void testOpenManageOrdersAndSearchOrderId() {
		
		CommonOps commonOps = new CommonOps();

		Xls_Reader xr=new Xls_Reader(testData); int
		i=Integer.parseInt(Row);
		String orderId=xr.getCellData("Input","OrderId", i).trim();

		commonOps.openManageOrdersPageAndSearchOrder(orderId);
	}
}
