package com.mystore.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.qa.base.BaseClass;
import com.mystore.qa.pages.HomePage;
import com.mystore.qa.pages.LandingPage;
import com.mystore.qa.pages.LoginPage;

public class LoginPageTest extends BaseClass {

	LoginPage loginPage;
	LandingPage landingPage;
	HomePage homePage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		landingPage=new LandingPage();
		loginPage= landingPage.clickSignIn();
	}
	
	@Test(priority=1)
	public void loginTest() {
		homePage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		

	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
