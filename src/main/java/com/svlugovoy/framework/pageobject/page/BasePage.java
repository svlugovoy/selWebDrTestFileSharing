package com.svlugovoy.framework.pageobject.page;

import com.svlugovoy.framework.pageobject.PageObject;
import com.svlugovoy.framework.pageobject.block.LeftMenuNavigationBlock;
import com.svlugovoy.framework.pageobject.block.TopMenuBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public abstract class BasePage extends PageObject {

    private static final String CSS_TOP_BLOCK = "#header-outside";
    private static final String CSS_MENU_NAVIGATION_BLOCK = "#menuBox";

    public TopMenuBlock getTopMenuBlock() {
        WebElement topMenu = driver.findElement(By.cssSelector(CSS_TOP_BLOCK));

        return new TopMenuBlock(topMenu);
    }

    public LeftMenuNavigationBlock getMenuNavigation() {
        WebElement leftMenu = driver.findElement(By.cssSelector(CSS_MENU_NAVIGATION_BLOCK));

        return new LeftMenuNavigationBlock(leftMenu);
    }

}
