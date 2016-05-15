package com.svlugovoy.framework.pageobject;

import com.svlugovoy.framework.utils.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */
public abstract class PageObject {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObject() {
        driver = DriverManager.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, 30, 300);
        PageFactory.initElements(driver, this);
    }

    public Alert switchToAlert() {
        try {
            return driver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    public Boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public String getTextFromAlert() {

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.dismiss();

        return text;
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }
}
