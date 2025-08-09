package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		return "openauto"+System.currentTimeMillis()+"@open.com";
	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"automation", "shayma", "1212121214", "automk%341", "yes"},
			{"automation", "shaymb", "1212121215", "automk%342", "no"},
			{"automation", "shaymc", "1212121216", "automk%343", "yes"}
		};
	}
	@DataProvider
	public Object[][] getUserSheetData() {
		return ExcelUtil.getSheetTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getUserData")
	public void registerUserTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(regPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}

}
