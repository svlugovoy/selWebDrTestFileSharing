package com.svlugovoy.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class HomePageTest {
    private static final String URL = "https://secure.filelocker.com";
    private static final String LOGIN = "minchekov160@hotmail.com";
    private static final String PASSWORD = "Qw1111";
    private static final String CSS_LOGIN_FIELD = "#txtLogin";
    private static final String CSS_PASSWORD_FIELD = "#txtPassword";
    private static final String CSS_LOGIN_BUTTON = "#loginBtnSubmit";
    private static final String CSS_AJAX_PROGRESS = "img[src='/images/biz/ajax-progress2.gif']";
    private static final String CSS_LISTING_LOADED_ELEMENT = "#listing_loaded";
    private static final String CSS_FILE_LISTING_AREA = "#bizFileListing";
    private static final String CSS_FOLDER = ".dirEntry";

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 15, 250);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(URL);
    }

    @Test
    public void canVerifyContentOfHomePage() {
        //login
        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(LOGIN);
        driver.findElement(By.cssSelector(CSS_PASSWORD_FIELD)).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector(CSS_LOGIN_BUTTON)).click();

        //wait until content is loaded
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(CSS_AJAX_PROGRESS)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(CSS_LISTING_LOADED_ELEMENT)));

        //create Set of expected folders
        Set<String> expectedFolders = new HashSet<String>();
        expectedFolders.add("File Sharing");
        expectedFolders.add("test");

        //get Set of actual folders
        List<WebElement> foldersList = driver.findElements(By.cssSelector(CSS_FILE_LISTING_AREA + " " + CSS_FOLDER));
        Set<String> actualFolders = foldersList.stream().map(element -> element.getAttribute("bdg-name")).collect(Collectors.toSet());

        Assert.assertEquals(actualFolders, expectedFolders);
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
