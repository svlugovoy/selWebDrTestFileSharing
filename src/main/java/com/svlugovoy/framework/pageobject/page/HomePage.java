package com.svlugovoy.framework.pageobject.page;

import org.openqa.selenium.By;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class HomePage extends BasePage {

    static final String ID_UPLOAD_BOX = "uploadBox";

    public String getUserBoxInfo() {
        return driver.findElement(By.cssSelector(".info")).getAttribute("data");
    }

}
