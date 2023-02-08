package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class SignInPage {

	WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement usernameTextField() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[contains(@id,'Email')]"), 30);
	}

	public WebElement passowrdTextField() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[contains(@id,'Password')]"), 30);
	}
	
	public WebElement loginButton() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[contains(@type,'submit')]"), 30);
	}
	
	public WebElement forgotPassword() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Forgot password?"), 30);
	} 

}
