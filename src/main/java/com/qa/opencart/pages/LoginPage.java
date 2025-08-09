package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	//private by locators
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	
	//public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step(".... getting Login page title.....")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login page title is : "+title);
		return title;
	}
	
	@Step(".... getting Login page URL.....")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login page URL is : "+url);
		return url;
	}
	
	@Step(".... is fogot pwd link exists or not.....")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	@Step(".... Login to app with username: {0} and password: {1}.....")
	public AccountsPage doLogin(String username , String pwd) {
		System.out.println("App creds are: "+username+" : "+pwd);
		eleUtil.waitForElementVisible(email, AppConstants.MEDIUM_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step(".... navigate to Register page.....")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, AppConstants.MEDIUM_TIME_OUT).click();
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
