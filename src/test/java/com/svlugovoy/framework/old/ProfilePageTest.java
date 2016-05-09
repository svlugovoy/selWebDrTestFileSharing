package com.svlugovoy.framework.old;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 02.05.2016.
 */
public class ProfilePageTest {

    public static final String URL = "https://secure.filelocker.com/login";
    public static final String LOGIN = "minchekov160@hotmail.com";
    public static final String PSWD = "Qw1111";
    public static final String CSS_LOGIN_FIELD = "#txtLogin";
    public static final String CSS_PSWD_FIELD = "#txtPassword";
    public static final String CSS_LOGIN_BTN = "#loginBtnSecText p";
    public static final String ERR_MESS_FIRST_NAME =
            "First Name must have length from 2 to 30 and may contain only letters, spaces, dot and dash characters";
    public static final String ERR_MESS_LAST_NAME =
            "Last Name must have length from 2 to 30 and may contain only letters, spaces, dot and dash characters";
    public static final String ERR_MESS_COMPANY =
            "Company must have length from 2 to 100, start with letter and may contain only letters, digits, " +
                    "spaces, dot and dash characters";
    public static final String ERR_MESS_WEB = "Website should have valid URL format";
    public static final String ERR_MESS_PHONE = "Phone Number must have length from 7 to 20 characters and may " +
            "contain only digits, plus characters and parentheses and may start with plus character";

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
        login();
    }

    @Test
    public void canUpdateProfileInfoWithValidData() {

        driver.findElement(By.cssSelector(".navBoxHeader.menu-settings")).click();
        driver.findElement(By.cssSelector(".navBoxLink a[href='/settings']")).click();

        waitForLoad(driver);

        driver.findElement(By.cssSelector("input[name='s_first_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_first_name']")).sendKeys("John");

        driver.findElement(By.cssSelector("input[name='s_last_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_last_name']")).sendKeys("Smith");

        driver.findElement(By.cssSelector("input[name='s_company_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_company_name']")).sendKeys("Epam");

        driver.findElement(By.cssSelector("input[name='s_website']")).clear();
        driver.findElement(By.cssSelector("input[name='s_website']")).sendKeys("https://prog.kiev.ua/");

        driver.findElement(By.cssSelector("input[name='s_title']")).clear();
        driver.findElement(By.cssSelector("input[name='s_title']")).sendKeys("Superman");

        driver.findElement(By.cssSelector("input[name='s_phone']")).clear();
        driver.findElement(By.cssSelector("input[name='s_phone']")).sendKeys("067-333-55-77");

        Select selectTimeZone = new Select(driver.findElement(By.cssSelector("select[name='s_timezone']")));
        selectTimeZone.selectByVisibleText("Europe/Kiev");

        Select selectItemPerPage = new Select(driver.findElement(By.cssSelector("select[id='s_file_listing_page_size']")));
        selectItemPerPage.selectByVisibleText("20");

        driver.findElement(By.cssSelector("input[id='s_policy_file_age']")).clear();
        driver.findElement(By.cssSelector("input[id='s_policy_file_age']")).sendKeys("365");

        driver.findElement(By.cssSelector("input[name='s_display_pic']")).sendKeys("E:\\123.jpg");

        driver.findElement(By.cssSelector("td #btnSaveChanges")).click();

        assertEquals(driver.findElement(By.cssSelector("input[name='s_first_name']")).getAttribute("value"), "John");
        assertEquals(driver.findElement(By.cssSelector("input[name='s_last_name']")).getAttribute("value"), "Smith");
        assertEquals(driver.findElement(By.cssSelector("input[name='s_company_name']")).getAttribute("value"), "Epam");
        assertEquals(driver.findElement(By.cssSelector("input[name='s_website']")).getAttribute("value"), "https://prog.kiev.ua/");
        assertEquals(driver.findElement(By.cssSelector("input[name='s_title']")).getAttribute("value"), "Superman");
        assertEquals(driver.findElement(By.cssSelector("input[name='s_phone']")).getAttribute("value"), "067-333-55-77");
        selectTimeZone = new Select(driver.findElement(By.cssSelector("select[name='s_timezone']")));
        assertEquals(selectTimeZone.getFirstSelectedOption().getText(), "Europe/Kiev");
        selectItemPerPage = new Select(driver.findElement(By.cssSelector("select[id='s_file_listing_page_size']")));
        assertEquals(selectItemPerPage.getFirstSelectedOption().getText(), "20");
        assertEquals(driver.findElement(By.cssSelector("input[id='s_policy_file_age']")).getAttribute("value"), "365");
        assertNotEquals("/images/biz/large_avatar.gif", driver.findElement(By.cssSelector("img[id='imgUserLogo']"))
                .getAttribute("src"));
    }

    @Test(dataProvider = "unBoundaryData")
    public void canNotUpdateProfileInfoWithUnBoundaryData(
            String firstName, String lastName, String company, String web, String phone, String message) {

        driver.get("https://secure.filelocker.com/settings");
        waitForLoad(driver);

        driver.findElement(By.cssSelector("input[name='s_first_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_first_name']")).sendKeys(firstName);

        driver.findElement(By.cssSelector("input[name='s_last_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_last_name']")).sendKeys(lastName);

        driver.findElement(By.cssSelector("input[name='s_company_name']")).clear();
        driver.findElement(By.cssSelector("input[name='s_company_name']")).sendKeys(company);

        driver.findElement(By.cssSelector("input[name='s_website']")).clear();
        driver.findElement(By.cssSelector("input[name='s_website']")).sendKeys(web);

        driver.findElement(By.cssSelector("input[name='s_phone']")).clear();
        driver.findElement(By.cssSelector("input[name='s_phone']")).sendKeys(phone);

        driver.findElement(By.cssSelector("td #btnSaveChanges")).click();

        assertEquals(message, driver.findElement(By.cssSelector("div[id='frmError']")).getText());

    }

    @DataProvider
    public Object[][] unBoundaryData() {
        return new Object[][]{
                {"12345", "Smith", "Epam", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_FIRST_NAME},
                {"A", "Smith", "Epam", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_FIRST_NAME},
                {"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", "Smith", "Epam", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_FIRST_NAME},
                {"John", "Smith1", "Epam", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_LAST_NAME},
                {"John", "S", "Epam", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_LAST_NAME},
                {"John", "SmithSmithSmithSmithSmithSmithSmith", "Epam", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_LAST_NAME},
                {"John", "Smith", "1Abcde", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_COMPANY},
                {"John", "Smith", "asd/aaaa", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_COMPANY},
                {"John", "Smith", "A", "https://prog.kiev.ua/", "067-333-55-77", ERR_MESS_COMPANY},
                {"John", "Smith", "Epam", "prog.kiev.ua", "067-333-55-77", ERR_MESS_WEB},
                {"John", "Smith", "Epam", "123", "067-333-55-77", ERR_MESS_WEB},
                {"John", "Smith", "Epam", "https://prog.kiev.ua/", "067-33", ERR_MESS_PHONE},
                {"John", "Smith", "Epam", "https://prog.kiev.ua/", "qwe067-33", ERR_MESS_PHONE},
                {"John", "Smith", "Epam", "https://prog.kiev.ua/", "123456789123456789123", ERR_MESS_PHONE},
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
