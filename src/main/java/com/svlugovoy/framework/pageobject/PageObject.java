package com.svlugovoy.framework.pageobject;

import com.svlugovoy.framework.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */
public abstract class PageObject {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObject(){
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, 30, 200);
    }




}
