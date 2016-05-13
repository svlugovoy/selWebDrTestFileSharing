package com.svlugovoy.framework.pageobject.block;

import com.svlugovoy.framework.pageobject.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */
public class TopMenuBlock extends PageObject {

    private static final String CSS_TITLE_ELEMENT = ".info[title]";
    private static final String CSS_FULLNAME_ELEMENT = ".name a";

    private final WebElement topMenuBlock;

    public TopMenuBlock(WebElement topMenu) {
        this.topMenuBlock = topMenu;
    }

    public String getUsername() {
        return topMenuBlock.findElement(By.cssSelector(CSS_TITLE_ELEMENT)).getAttribute("data");
    }

    public String getFullName() {
        return topMenuBlock.findElement(By.cssSelector(CSS_FULLNAME_ELEMENT)).getText();
    }

}
