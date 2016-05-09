package com.svlugovoy.framework.pageobject.page;

import com.svlugovoy.framework.pageobject.PageObject;
import org.openqa.selenium.By;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class LoginPage extends PageObject {

    private static final String URL = "https://secure.filelocker.com";
    private static final String LOGIN = "minchekov160@hotmail.com";
    private static final String PASSWORD = "Qw1111";
    private static final String CSS_LOGIN_FIELD = "#txtLogin";
    private static final String CSS_PASSWORD_FIELD = "#txtPassword";
    private static final String CSS_LOGIN_BUTTON = "#loginBtnSubmit";

    public void open() {
        driver.get(URL);
    }

    public HomePage loginAs(String login, String password) {
        driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)).sendKeys(LOGIN);
        driver.findElement(By.cssSelector(CSS_PASSWORD_FIELD)).sendKeys(PASSWORD);

        driver.findElement(By.cssSelector(CSS_LOGIN_BUTTON)).click();

        return new HomePage();
    }

}
