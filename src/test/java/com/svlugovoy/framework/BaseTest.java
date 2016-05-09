package com.svlugovoy.framework;

import com.svlugovoy.framework.pageobject.page.LoginPage;
import com.svlugovoy.framework.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public abstract class BaseTest {

    @BeforeClass
    public void beforeClass() {
        WebDriver driver = DriverManager.createDriver("firefox");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
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