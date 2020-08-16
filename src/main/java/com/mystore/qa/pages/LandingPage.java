package com.mystore.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.qa.base.BaseClass;

public class LandingPage extends BaseClass{

	public LandingPage() {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[@class='login']")
	WebElement signInBtn;
	
	public LoginPage clickSignIn() {
		signInBtn.click();
		
		return new LoginPage();
	}
}
