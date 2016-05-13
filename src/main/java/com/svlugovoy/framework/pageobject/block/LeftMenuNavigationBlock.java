package com.svlugovoy.framework.pageobject.block;

import com.svlugovoy.framework.pageobject.PageObject;
import com.svlugovoy.framework.pageobject.page.HomePage;
import com.svlugovoy.framework.pageobject.page.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */
public class LeftMenuNavigationBlock extends PageObject {

    private static final String CSS_MENU_FILES = ".navBoxHeader.menu-files";
    private static final String CSS_FILES_SUBMENU_BOX = ".navBox.menu-files";
    private static final String CSS_SUBMENU_ALL_FILES = "a[href='/files']";

    private static final String CSS_MENU_SETTINGS = ".navBoxHeader.menu-settings";
    private static final String CSS_SETTINS_SUBMENU_BOX = ".navBox.menu-settings";
    private static final String CSS_SUBMENU_PROFILE = "a[href='/settings']";

    private final WebElement menuNavigationBlock;

    public LeftMenuNavigationBlock(WebElement menuNavigationBlock) {
        this.menuNavigationBlock = menuNavigationBlock;
    }

    public HomePage selectHomePage() {
        selectMenu(LeftMenu.ALL_FILES);

        return new HomePage();
    }

    public ProfilePage selectProfilePage() {
        selectMenu(LeftMenu.PROFILE);

        return new ProfilePage();
    }

    private void selectMenu(LeftMenu menu) {
        //click on parent menu element
        WebElement menuElement = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(menu.menuCss)));
        menuElement.click();

        //wait until parent menu is completely opened
        wait.until((WebDriver input) -> {
            WebElement subMenuBox = menuNavigationBlock.findElement(By.cssSelector(menu.submenuBoxCss));

            return subMenuBox.getAttribute("style").isEmpty() || subMenuBox.getAttribute("style").contains("height: auto");
        });

        //click on submenu element
        driver.findElement(By.cssSelector(menu.submenuCss)).click();
    }

    private enum LeftMenu {
        ALL_FILES(CSS_MENU_FILES, CSS_SUBMENU_ALL_FILES, CSS_FILES_SUBMENU_BOX),
        PROFILE(CSS_MENU_SETTINGS, CSS_SUBMENU_PROFILE, CSS_SETTINS_SUBMENU_BOX);

        private String menuCss;
        private String submenuCss;
        private String submenuBoxCss;

        LeftMenu(String menuCss, String submenuCss, String submenuBoxCss) {
            this.menuCss = menuCss;
            this.submenuCss = submenuCss;
            this.submenuBoxCss = submenuBoxCss;
        }
    }

}
