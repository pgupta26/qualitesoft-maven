package test.java.com.qualitesoft.freightclub.testscripts.forgotpassword;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.JavaFunction;
import test.java.com.qualitesoft.core.Log;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.WaitTool;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.pageobjects.Mailinator;
import test.java.com.qualitesoft.freightclub.pageobjects.ResetPasswordPage;

public class TestResetPassword extends InitializeTest {
	
	@Test
	public void testResetPassword() {
			Xls_Reader xr=new Xls_Reader(testData);
			int rowIndex = Integer.parseInt(Row);
			
			String emailAddress = xr.getCellData("CreateAccount", "Email", rowIndex);			
			String newPassword = "Automation"+JavaFunction.getRandomNumber(1000, 10000)+"!";
			Log.info("New Password For Reset: "+newPassword);
			
			SeleniumFunction.executeJS(driver, "window.open('');");
			try {
				SeleniumFunction.getCurrentWindow(driver);
				SeleniumFunction.open(driver, "https://yopmail.com/en/");

				Mailinator mailinator = new Mailinator(driver);
				ResetPasswordPage resetPassword = new ResetPasswordPage(driver);

				SeleniumFunction.sendKeys(mailinator.searchBoxYopMail(), emailAddress);
				mailinator.searchBoxYopMail().sendKeys(Keys.ENTER);
				WaitTool.sleep(20);
				SeleniumFunction.selectFrame(driver, "ifinbox");
						SeleniumFunction.click(mailinator.firstYopEmail());
				SeleniumFunction.selectParentframe(driver);
				SeleniumFunction.selectFrame(driver, "ifmail");
						SeleniumFunction.click(resetPassword.clickHereToOpenResetPasswordPage());
						try {
							SeleniumFunction.getCurrentWindow(driver);
							WaitTool.sleep(10);
						
							//enter email id
							SeleniumFunction.sendKeys(resetPassword.email(), emailAddress);
						
							//enter password
							SeleniumFunction.sendKeys(resetPassword.password(),newPassword);
						
							//enter confirm password
							SeleniumFunction.sendKeys(resetPassword.confirmPassword(), newPassword);
						
							//click on reset button
							SeleniumFunction.click(resetPassword.reset());
							
							//verify reset password confirmation
							Assert.assertTrue(resetPassword.resetPasswordConfirmation().getText().contains("Your password has been reset."));
							
							//update new password in sheet
							xr.setCellData("CreateAccount", "Password", rowIndex, newPassword);
						SeleniumFunction.closeWindow(driver);
						SeleniumFunction.getCurrentWindow(driver);
							
						}catch(Exception | AssertionError ex) {
							SeleniumFunction.closeWindow(driver);
							SeleniumFunction.getCurrentWindow(driver);
							Assert.fail(ex.getMessage());
						}
				SeleniumFunction.selectParentframe(driver);
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
			}catch(Exception ex) {
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				Assert.fail(ex.getMessage());
			}
	}

}
