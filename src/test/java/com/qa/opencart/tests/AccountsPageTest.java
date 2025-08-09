package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("EPIC-102 : Design of the Accounts page for OpenCart appp")
@Story("US-202 : Implement of the Accounts Page features for OpenCart app")
public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void accsPageSetup() {
		accsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accsPageTitleTest() {
		String actAccsPageTitle = accsPage.getAccsPageTitle();
		System.out.println("Accounts page title is: " + actAccsPageTitle);
		Assert.assertEquals(actAccsPageTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(accsPage.isLogoutLinkExist());
	}

	@Test
	public void accsPageHeaderCountTest() {
		System.out.println("Accounts Page Header Count: "+accsPage.getAccsPageHeaderCount());
		Assert.assertEquals(accsPage.getAccsPageHeaderCount(), AppConstants.ACCOUNTS_PAGE_HEADER_COUNT);
	}

	@Test
	public void accsPageHeaderTest() {
		List<String> actAccsPageHeaderList = accsPage.getAccsPageHeader();
		Assert.assertEquals(actAccsPageHeaderList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] { { "macbook", 3 }, { "samsung", 2 }, { "canon", 1 }, {"imac",1} };
	}

	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchKey, int expProdCount) {
		searchResPage = accsPage.doSearch(searchKey);
		int actSearchResultsCount = searchResPage.getSearchResusltsCount();
		Assert.assertEquals(actSearchResultsCount, expProdCount);
	}

}
