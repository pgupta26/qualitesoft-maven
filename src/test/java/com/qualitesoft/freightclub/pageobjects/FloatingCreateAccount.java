package test.java.com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.java.com.qualitesoft.core.WaitTool;

public class FloatingCreateAccount {

	WebDriver driver;

	public FloatingCreateAccount(WebDriver driver) {
		this.driver = driver;
	}
	public WebElement firstName() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//form[@class='form-horizontal']/div[2]/div/div[2]/div[2]//input"), 50);
	}
	public WebElement lastName() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//form[@class='form-horizontal']/div[2]/div/div[3]/div[2]//input"), 30);
	}
	public WebElement companyName() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//form[@class='form-horizontal']/div[2]/div/div[4]/div[2]//input"), 30);
	}
	public WebElement password() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//form[@class='form-horizontal']/div[2]/div/div[6]/div[2]//input"), 30);
	}
	public WebElement confirmPassword() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//form[@class='form-horizontal']/div[2]/div/div[7]/div[2]//input"), 30);
	}
	public WebElement phone() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//form[@class='form-horizontal']/div[2]/div/div[8]/div[2]//input"), 30);
	}
	
	public WebElement extension() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='loginbox']/form/div[2]/div/div[8]/div[4]/input"), 30);
	}
	
	public WebElement agreeCheckbox() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("IAgree"), 30);
	}
	public WebElement createButton() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Create Account')]"), 30);
	}
	
	public WebElement confirmAcceptance() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("btnAcceptTaC"), 30);
	}
	
	public WebElement accountCreateSuccessMessage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h2[text()='Accept Terms and Conditions']/following::p"), 30);
	}
	public WebElement closeButton() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Close')]"), 30);
	}		
}
