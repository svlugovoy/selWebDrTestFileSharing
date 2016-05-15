package com.svlugovoy.framework;

import com.svlugovoy.framework.login_page.LoginTest;
import com.svlugovoy.framework.pageobject.page.HomePage;
import com.svlugovoy.framework.pageobject.page.LoginPage;
import com.svlugovoy.framework.utils.DriverManager;
import com.svlugovoy.framework.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public abstract class BaseTest {

    protected HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        WebDriver driver = DriverManager.getInstance().createDriver(PropertyLoader.getInstance().getBrowserName());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeBaseMethod(Method method) {
        LoginPage loginPage = new LoginPage();
        loginPage.open();

        if (method.getDeclaringClass().equals(LoginTest.class)) {
            return;
        }

        homePage = loginPage.loginAs(PropertyLoader.getInstance().getUsername(), PropertyLoader.getInstance().getPassword());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        DriverManager.getInstance().getDriver().manage().deleteAllCookies();

    }

    @AfterClass
    public void afterClass() {
        DriverManager.getInstance().getDriver().quit();
    }

    protected void assertInstanceOf(Object obj, Class<?> clazz) {
        String msg = String.format("Expected instance of <%1$s> class, but was <%2$s>",
                clazz.getSimpleName(), obj == null ? null : obj.getClass().getSimpleName());

        if (!clazz.isInstance(obj)) {
            Assert.fail(msg);
        }
    }
}