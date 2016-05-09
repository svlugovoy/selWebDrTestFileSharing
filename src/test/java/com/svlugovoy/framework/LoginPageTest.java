package com.svlugovoy.framework;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class LoginPageTest extends BaseTest{
    private static final String URL = "https://secure.filelocker.com";
    private static final String LOGIN = "minchekov160@hotmail.com";
    private static final String PASSWORD = "Qw1111";
    private static final String CSS_LOGIN_FIELD = "#txtLogin";
    private static final String CSS_PASSWORD_FIELD = "#txtPassword";
    private static final String CSS_LOGIN_BUTTON = "#loginBtnSubmit";
    private static final String CSS_TITLE_ELEMENT = ".info[title]";
    private static final String CSS_ALERT_MESSAGE = "#alertBox center";
    private static final String UNABLE_TO_VERIFY_CREDENTIALS_ERROR_MESSAGE = "Unable to verify credentials";

    @Test
    public void canLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.loginAs(LOGIN, PASSWORD);

        String info = homePage.getUserBoxInfo();

        assertEquals(info, LOGIN);
    }

    @Test
    public void canNotLoginWithIncorrectCredentials() {
        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(RandomStringUtils.randomAlphanumeric(1));
        driver.findElement(By.cssSelector(CSS_PASSWORD_FIELD)).sendKeys(PASSWORD);

        driver.findElement(By.cssSelector(CSS_LOGIN_BUTTON)).click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_ALERT_MESSAGE)));

        Assert.assertEquals(errorMessage.getText() + "1", UNABLE_TO_VERIFY_CREDENTIALS_ERROR_MESSAGE);
    }

    @Test
    public void canAcceptAlert() {
        driver.findElement(By.cssSelector(CSS_LOGIN_BUTTON)).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}

