package com.svlugovoy.framework.old;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 02.05.2016.
 */
public class HomePageTest {

    public static final String URL = "https://secure.filelocker.com/login";
    public static final String LOGIN = "minchekov160@hotmail.com";
    public static final String PSWD = "Qw1111";
    public static final String CSS_LOGIN_FIELD = "#txtLogin";
    public static final String CSS_PSWD_FIELD = "#txtPassword";
    public static final String CSS_LOGIN_BTN = "#loginBtnSecText p";

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get(URL);
        login();
        waitForLoad(driver);
    }

    @Test
    public void isExistRootFolder() {

        String text = null;

        for (WebElement e : driver.findElements(By.cssSelector(".dirName"))) {
            if (e.getText().equals("File Sharing")) {
                text = e.getText();
            }
        }

        assertNotNull(text);

    }

    @Test
    public void canCreateAndDeleteFolder() {

        String parentWindowHandler = driver.getWindowHandle();

        driver.findElement(By.cssSelector("#createFolderBtn")).click();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        String nameFolder = "test folder12";
        driver.findElement(By.cssSelector("input[id='folder_name']")).sendKeys(nameFolder);
        driver.findElement(By.cssSelector("a[onclick='createFolderWindowed(); return false;']")).click();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        driver.findElement(By.cssSelector(".closeButton.biz_alert_close")).click();

        driver.switchTo().window(parentWindowHandler);
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, 30, 200);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dirName")));

        String text = null;
        for (WebElement e : driver.findElements(By.cssSelector(".dirName"))) {
            if (e.getText().equals(nameFolder)) {
                text = e.getText();
            }
        }

        assertNotNull(text);

        parentWindowHandler = driver.getWindowHandle();
        driver.findElement(By.cssSelector("li[title='" + nameFolder + "'] div.dirActionButton")).click();
        driver.findElement(By.cssSelector("a[title='Remove Folder']")).click();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        driver.findElement(By.cssSelector("a[onclick='window.removeFolderFormFunc.confirmRemoveFolder()'] div.bizButton")).click();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        driver.findElement(By.cssSelector(".closeButton.biz_alert_close")).click();

        driver.switchTo().window(parentWindowHandler);
        driver.navigate().refresh();

        wait = new WebDriverWait(driver, 30, 200);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dirName")));

        text = null;
        for (WebElement e : driver.findElements(By.cssSelector(".dirName"))) {
            if (e.getText().equals(nameFolder)) {
                text = e.getText();
            }
        }

        assertEquals(text, null);

    }


    @Test
    public void canUploadFile() {

    }

    @Test
    public void canDeleteFile() {

    }

    @Test
    public void canUseSearchForm() {

    }


    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    private void login() {
        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(LOGIN);
        driver.findElement(By.cssSelector(CSS_PSWD_FIELD)).sendKeys(PSWD);
        driver.findElement(By.cssSelector(CSS_LOGIN_BTN)).click();
    }

    void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

}
