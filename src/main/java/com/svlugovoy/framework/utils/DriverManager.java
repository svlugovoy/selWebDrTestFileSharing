package com.svlugovoy.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class DriverManager {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String CHROME_DRIVER_PATH = "src/main/resources/chrome/chromedriver.exe";

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    //Singleton
    private final static DriverManager INSTANCE = new DriverManager();

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        return INSTANCE;
    }

    //Factory method
    public WebDriver createDriver(String browser) {

        switch (browser.toLowerCase()) {
            case CHROME:
                setChromeDriver();
                webDriver.set(new ChromeDriver());
                break;
            case FIREFOX:
                webDriver.set(new FirefoxDriver());
                break;
            default:
                throw new UnsupportedOperationException(String.format("Browser %1$s is not supported!", browser));
        }

        return webDriver.get();
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }

    private void setChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
    }

}
