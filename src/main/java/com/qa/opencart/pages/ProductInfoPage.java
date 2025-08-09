package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.name("quantity");
	private By addToCart = By.id("button-cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String actProductHeader = eleUtil.doElementGetText(productHeader);
		System.out.println("Actual Product Header Value ==> "+actProductHeader);
		return actProductHeader;
	}
	
	public int getProductImagesCount() {
		int actProductImagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println("Actual Product images count for : "+getProductHeader()+" is :"+actProductImagesCount);
		return actProductImagesCount;
	}
	private void getProductMetaData() {
		List<WebElement> productMetaList = eleUtil.waitForElementsVisible(productMetaData, AppConstants.MEDIUM_TIME_OUT);
		for(WebElement e : productMetaList) {
			String metaText = e.getText();
			String key = metaText.split(":")[0].trim();
			String value = metaText.split(":")[1].trim();
			productInfoMap.put(key, value);			
		}
	}
	private void getPriceData() {
		List<WebElement> productPriceList = eleUtil.waitForElementsVisible(productPriceData, AppConstants.MEDIUM_TIME_OUT);
		String priceValue = productPriceList.get(0).getText();
		productInfoMap.put("price", priceValue);
		productInfoMap.put(productPriceList.get(1).getText().split(":")[0].trim(), productPriceList.get(1).getText().split(":")[1].trim());		
	}
	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("productname", getProductHeader());
		productInfoMap.put("imagescount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getPriceData();
		return productInfoMap;		
	}
	
	
	
	
	
	

}
