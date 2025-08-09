package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("EPIC-103 : Design of the ProductInfo page for OpenCart appp")
@Story("US-203 : Implement of the ProductInfo Page features for OpenCart app")
public class ProductInfoTest extends BaseTest{
	@BeforeClass
	public void productInfoSetup() {
		accsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] getProductSearchData() {
		return new Object[][] {
			{"macbook","MacBook Air"},
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
		};
	}
	@Test(dataProvider = "getProductSearchData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResPage = accsPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String actProdInfoHeaderValue = productInfoPage.getProductHeader();
		Assert.assertEquals(actProdInfoHeaderValue, productName);		
	}	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"macbook","MacBook Air",4},
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
		};
	}	
	@Test(dataProvider = "productData")
	public void productImagesCountTest(String searchKey, String productName, int expProductImagesCount) {
		searchResPage = accsPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount, expProductImagesCount);
	}
	@Test()
	public void productInfoTest() {
		searchResPage = accsPage.doSearch("macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfo = productInfoPage.getProductInfo();
		System.out.println(actProductInfo);
		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("imagescount"), "4");
		softAssert.assertEquals(actProductInfo.get("price"), "$2,000.00");
		softAssert.assertEquals(actProductInfo.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actProductInfo.get("productname"), "MacBook Pro");
		softAssert.assertAll();
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
