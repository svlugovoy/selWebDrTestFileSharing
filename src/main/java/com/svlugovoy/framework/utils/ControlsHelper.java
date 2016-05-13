package com.svlugovoy.framework.utils;

import org.openqa.selenium.WebElement;

public class ControlsHelper {

    public static void fillTextBox(WebElement textBox, String value) {
        if (textBox.isEnabled()) {
            textBox.clear();
            textBox.sendKeys(value);
        } else {
            throw new UnsupportedOperationException("Text box is not enabled");
        }
    }
}
