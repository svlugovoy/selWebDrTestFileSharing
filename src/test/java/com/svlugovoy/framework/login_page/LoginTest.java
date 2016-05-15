package com.svlugovoy.framework.login_page;

import com.svlugovoy.framework.BaseTest;
import com.svlugovoy.framework.pageobject.PageObject;
import com.svlugovoy.framework.pageobject.page.HomePage;
import com.svlugovoy.framework.pageobject.page.LoginPage;
import com.svlugovoy.framework.utils.PropertyLoader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class LoginTest extends BaseTest {

    private final String LOGIN = PropertyLoader.getInstance().getUsername();
    private final String PASSWORD = PropertyLoader.getInstance().getPassword();
    private static final String UNABLE_TO_VERIFY_CREDENTIALS_ERROR_MESSAGE = "Unable to verify credentials";

    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage();
    }

    //Successful login
    @Test
    public void canLoginWithValidCredentials() {
        HomePage homePage = loginPage.loginAs(LOGIN, PASSWORD);

        String actualUsername = homePage.getTopMenuBlock().getUsername();

        assertEquals(actualUsername, LOGIN);
    }

    //Login with invalid credentials
    @Test
    public void canNotLoginWithIncorrectLogin() {
        PageObject page = loginPage.tryLoginAs(LOGIN, "incorrect pasword");
        assertInstanceOf(page, LoginPage.class);

        loginPage = (LoginPage) page;
        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage, UNABLE_TO_VERIFY_CREDENTIALS_ERROR_MESSAGE);
    }

    //Login with empty credentials
    @Test(dataProvider = "credentials")
    public void shouldAlertAppearsWithTextIfLogInWithEmptyCredentials(String login, String password, String message) {
        loginPage.login(login, password);

        Assert.assertEquals(loginPage.getTextFromAlert(), message);
    }

    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {LOGIN, "", "Password must be supplied"},
                {"", PASSWORD, "Username must be supplied"},
        };
    }
}

