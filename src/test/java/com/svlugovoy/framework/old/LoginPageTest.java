package com.svlugovoy.framework.old;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private static final Logger log = Logger.getLogger(LoginPageTest.class);

    public static final String URL = "https://secure.filelocker.com/login";
    public static final String LOGIN = "minchekov160@hotmail.com";
    public static final String PSWD = "Qw1111";
    public static final String CSS_LOGIN_FIELD = "#txtLogin";
    public static final String CSS_PSWD_FIELD = "#txtPassword";
    public static final String CSS_LOGIN_BTN = "#loginBtnSecText p";
    public static final String CSS_LOGED_USER_BOX = ".info";
    public static final String CSS_LOGOUT = "a[href='/logout']";
    public static final String CSS_FORM_LOGIN = "form[name='frmLogin']";
    public static final String CSS_ALERT_BOX = "#alertBox";
    public static final String MESSAGE_ALERTBOX = "Unable to verify credentials";

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void canLoginWithValidCredentials() {

        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(LOGIN);
        driver.findElement(By.cssSelector(CSS_PSWD_FIELD)).sendKeys(PSWD);
        driver.findElement(By.cssSelector(CSS_LOGIN_BTN)).click();

        String result = driver.findElement(By.cssSelector(CSS_LOGED_USER_BOX)).getAttribute("data");
        Assert.assertEquals(result, LOGIN);

    }

    @Test(invocationCount = 1, enabled = true)
    public void canLogOut() {

        log.info("Invoke login with valid credentials");
        canLoginWithValidCredentials();

        log.info("Find and push logout reference");
        driver.findElement(By.cssSelector(CSS_LOGOUT)).click();
        WebElement formLogin = driver.findElement(By.cssSelector(CSS_FORM_LOGIN));

        log.warn("Check if exist login form at main page");
        Assert.assertNotNull(formLogin);
    }

    @Test
    public void canNotLoginWithInCorrectCredentials() {

        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(PSWD);
        driver.findElement(By.cssSelector(CSS_PSWD_FIELD)).sendKeys(LOGIN);
        driver.findElement(By.cssSelector(CSS_LOGIN_BTN)).click();

        String result = driver.findElement(By.cssSelector(CSS_ALERT_BOX)).getText();
        Assert.assertEquals(result, MESSAGE_ALERTBOX);

    }

    @Test(dataProvider = "incorrectCredentials")
    public void isPresentAlertWithMessage(String login, String pswd, String message) {

        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(login);
        driver.findElement(By.cssSelector(CSS_PSWD_FIELD)).sendKeys(pswd);
        driver.findElement(By.cssSelector(CSS_LOGIN_BTN)).click();

        WebDriverWait wait = new WebDriverWait(driver, 30, 200);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), message);
        alert.dismiss();
    }

    @DataProvider
    public Object[][] incorrectCredentials() {
        return new Object[][]{
                {LOGIN, "", "Password must be supplied"},
                {"", PSWD, "Username must be supplied"},
        };
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
