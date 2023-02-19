package test.java.com.qualitesoft.freightclub.testscripts.forgotpassword;

import org.testng.annotations.Test;

import test.java.com.qualitesoft.core.InitializeTest;
import test.java.com.qualitesoft.core.Log;
import test.java.com.qualitesoft.core.SeleniumFunction;
import test.java.com.qualitesoft.core.UseAssert;
import test.java.com.qualitesoft.core.Xls_Reader;
import test.java.com.qualitesoft.freightclub.appcommon.Messages;
import test.java.com.qualitesoft.freightclub.pageobjects.ForgotPasswordPage;
import test.java.com.qualitesoft.freightclub.pageobjects.SignInPage;

public class TestForgotPassword extends InitializeTest { 
	
	@Test
	public void testForgotPassword() {
		try {
			SignInPage singIn = new SignInPage(driver);
			ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
			
			Xls_Reader xr=new Xls_Reader(testData);
			int rowIndex = Integer.parseInt(Row);
			
			String emailAddress = xr.getCellData("CreateAccount", "Email", rowIndex);
			Log.info("EmailAddress: "+emailAddress);
			
			//click on forgot password link
			SeleniumFunction.click(singIn.forgotPassword());
			
			//enter email id
			SeleniumFunction.sendKeys(forgotPassword.email(), emailAddress);
			
			//click on reset password button
			SeleniumFunction.click(forgotPassword.resetPassword());
			
			//verify success confirmation message
			UseAssert.assertEquals(forgotPassword.forgotPasswordSuccessConfirmation().getText().trim(), Messages.forgot_password_confirmation_success);
			
			//click return to sign in  page
			SeleniumFunction.click(forgotPassword.returnToSignInPage());
			
		}catch(Exception | AssertionError ex) {
			SeleniumFunction.get(driver, URL);
			throw ex;
		}
	}

}
