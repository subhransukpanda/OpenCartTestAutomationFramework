package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-101 : Design of the Login page for OpenCart appp")
@Story("US-201 : Implement of the Login Page features for OpenCart app")
public class LoginPageTest extends BaseTest {
	
	@Description("Login page title test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Description("Login page URL test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}

	@Description("Check forgot pwd link is exists on Login page ....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void isForgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Description("Check User is able to login to app with valid credentials....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {
		AccountsPage accsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accsPage.isLogoutLinkExist(), true);
	}
	@Description(".......subh test....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 5)
	public void subhTest() {
		
		Assert.assertEquals(false, true);
	}

}
