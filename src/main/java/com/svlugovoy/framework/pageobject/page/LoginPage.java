package com.svlugovoy.framework.pageobject.page;

import com.svlugovoy.framework.pageobject.PageObject;
import com.svlugovoy.framework.utils.ControlsHelper;
import com.svlugovoy.framework.utils.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class LoginPage extends PageObject {

    private static final String CSS_LOGIN_FIELD = "#txtLogin";
    private static final String CSS_PASSWORD_FIELD = "#txtPassword";
    private static final String CSS_LOGIN_BUTTON = "#loginBtnSecText p";
    private static final String CSS_ALERT_BOX = "#alertBox center";


    public void open() {
        driver.get(PropertyLoader.getInstance().getAppUrl());
    }

    public PageObject tryLoginAs(String login, String password) {

        String currentUrl = driver.getCurrentUrl();

        login(login, password);

        try {
            switchToAlert().accept();
        } catch (NullPointerException e) {
        }

        String newUrl = driver.getCurrentUrl();

        if (!currentUrl.equals(newUrl) && !(driver.findElements(By.id(HomePage.ID_UPLOAD_BOX)) == null)) {

            return new HomePage();
        }

        return new LoginPage();
    }

    public HomePage loginAs(String login, String password) {
        PageObject page = tryLoginAs(login, password);

        if (!(page instanceof HomePage)) {
            throw new IllegalArgumentException(
                    String.format("Couldn't login with valid credentials (%1$s//%2$s)", login, password));
        } else {
            return (HomePage) page;
        }
    }

    public void login(String login, String password) {
        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_LOGIN_FIELD)), login);
        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_PASSWORD_FIELD)), password);
        driver.findElement(By.cssSelector(CSS_LOGIN_BUTTON)).click();

    }

    public String getErrorMessage() {
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_ALERT_BOX)));

        return errorMessage.getText();
    }
}
