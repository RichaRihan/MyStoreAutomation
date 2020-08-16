package com.mystore.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.qa.base.BaseClass;

public class LoginPage extends BaseClass{
	
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="email")
	WebElement email;
	
	@FindBy(name="passwd")
	WebElement password;
	
	@FindBy(name="SubmitLogin")
	WebElement submitBtn;
	
	
	public HomePage login(String user , String pwd) {
		email.sendKeys(user);
		password.sendKeys(pwd);
		submitBtn.click();
		
		return new HomePage();
	}

}
