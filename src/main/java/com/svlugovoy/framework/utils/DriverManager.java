package com.svlugovoy.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class DriverManager {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String OPERA = "opera";
    private static final String IE = "ie";
    private static final String CHROME_DRIVER_PATH = "src/main/resources/chrome/chromedriver.exe";
    private static final String OPERA_DRIVER_PATH = "src/main/resources/opera/operadriver.exe";
    private static final String IE_DRIVER_PATH = "src/main/resources/ie/IEDriverServer.exe";

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
            case OPERA:
                setOperaDriver();
                webDriver.set(new OperaDriver());
                break;
            case IE:
                setIEDriver();
                webDriver.set(new InternetExplorerDriver());
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

    private void setOperaDriver() {
        System.setProperty("webdriver.opera.driver", OPERA_DRIVER_PATH);
    }

    private void setIEDriver() {
        System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
    }
}
