package com.mystore.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.qa.base.BaseClass;

public class HomePage extends BaseClass{

	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@title='Women']")
	WebElement womenLink;
	
	@FindBy(xpath="//a[@title='Evening Dresses']")
	WebElement eveningDressesLink;
	
	@FindBy(name="search_query")
	WebElement searchBox;
	
	@FindBy(name="submit_search")
	WebElement searchBtn;
	
	@FindBy(xpath="//span[@class='heading-counter']")
	WebElement searchResults;
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public void selectEveningDresses() {
		Actions a=new Actions(driver);
		a.moveToElement(womenLink).moveToElement(eveningDressesLink).click().build().perform();
	}
	
	public String searchItem(String itemName) {
		searchBox.sendKeys(itemName);
		searchBtn.click();
		String resultText = searchResults.getText();
		resultText.trim();
		String itemCount = resultText.substring(0,1);
		return itemCount;
}

}