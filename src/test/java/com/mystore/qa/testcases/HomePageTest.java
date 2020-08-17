package com.mystore.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mystore.qa.base.BaseClass;
import com.mystore.qa.pages.HomePage;
import com.mystore.qa.pages.LandingPage;
import com.mystore.qa.pages.LoginPage;
import com.mystore.qa.util.TestUtil;

public class HomePageTest extends BaseClass{
	
	HomePage homePage;
	LoginPage loginPage;
	LandingPage landingPage;
	int i=0;
	
	String sheetName="Search Items";
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		landingPage=new LandingPage();
		loginPage=landingPage.clickSignIn();
		homePage=loginPage.login(prop.getProperty("u"
				+ "sername"),prop.getProperty("password"));
	}
//	
//	@Test(priority=1)
//	public void validateTitleTest() {
//	String pageTitle="My account - My Store";
//	String homePageTitle = homePage.verifyHomePageTitle();
//	Assert.assertEquals(homePageTitle, pageTitle, "Home Page title not matched");
//	}
//	
//	@Test(priority=2)
//	public void getEveningDressesTest() {
//		homePage.selectEveningDresses();
//	}
	
	@DataProvider
	public Object[][] getTestData() {
		Object[][] data =TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority=3, dataProvider="getTestData")
	public void validateItemsCount(String itemName, String itemTotal) {
		String itemActualCount = homePage.searchItem(itemName);
		Assert.assertEquals(itemActualCount, itemTotal);
		TestUtil.writeData(sheetName, "PASSED", i+1, 2);
		i++;
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}