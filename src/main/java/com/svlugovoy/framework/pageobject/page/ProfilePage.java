package com.svlugovoy.framework.pageobject.page;

import com.svlugovoy.framework.dataobject.ProfileData;
import com.svlugovoy.framework.utils.ControlsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage extends BasePage {

    private static final String CSS_FIRST_NAME_FIELD = "input[name='s_first_name']";
    private static final String CSS_LAST_NAME_FIELD = "input[name='s_last_name']";
    private static final String CSS_COMPANY_NAME_FIELD = "input[name='s_company_name']";
    private static final String CSS_WEBSITE_FIELD = "input[name='s_website']";
    private static final String CSS_TITLE_FIELD = "input[name='s_title']";
    private static final String CSS_PHONE_FIELD = "input[name='s_phone']";
    private static final String CSS_TIMEZONE_DROPDOWN = "select[name='s_timezone']";
    private static final String CSS_MAX_FILE_AGE_FIELD = "input[name='s_policy_file_age']";
    private static final String CSS_ITEMS_PER_PAGE_DROPDOWN = "select[name='s_file_listing_page_size']";
    private static final String CSS_SAVE_CHANGES_BUTTON = "#btnSaveChanges";
    public static final String CSS_ERROR_MESSAGE_BOX = "div[id='frmError']";

    public ProfileData getProfileData() {

        return new ProfileData(
                driver.findElement(By.cssSelector(CSS_FIRST_NAME_FIELD)).getAttribute("value"),
                driver.findElement(By.cssSelector(CSS_LAST_NAME_FIELD)).getAttribute("value"),
                driver.findElement(By.cssSelector(CSS_COMPANY_NAME_FIELD)).getAttribute("value"),
                driver.findElement(By.cssSelector(CSS_WEBSITE_FIELD)).getAttribute("value"),
                driver.findElement(By.cssSelector(CSS_TITLE_FIELD)).getAttribute("value"),
                driver.findElement(By.cssSelector(CSS_PHONE_FIELD)).getAttribute("value"),
                driver.findElement(By.cssSelector(CSS_TIMEZONE_DROPDOWN)).getAttribute("value"),
                Integer.parseInt(driver.findElement(By.cssSelector(CSS_ITEMS_PER_PAGE_DROPDOWN)).getAttribute("value")),
                Integer.parseInt(driver.findElement(By.cssSelector(CSS_MAX_FILE_AGE_FIELD)).getAttribute("value")));
    }

    public ProfilePage editProfile(ProfileData profile) {

        fillProfileData(profile);

        driver.findElement(By.cssSelector(CSS_SAVE_CHANGES_BUTTON)).click();
        return new ProfilePage();
    }

    private void fillProfileData(ProfileData profile) {

        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_FIRST_NAME_FIELD)), profile.getFirstName());
        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_LAST_NAME_FIELD)), profile.getLastName());
        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_COMPANY_NAME_FIELD)), profile.getCompanyName());
        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_WEBSITE_FIELD)), profile.getWebsite());
        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_TITLE_FIELD)), profile.getTitle());
        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_PHONE_FIELD)), profile.getPhone());
        WebElement webTimeZoneDropdown = driver.findElement(By.cssSelector(CSS_TIMEZONE_DROPDOWN));
        if (webTimeZoneDropdown.isEnabled()) {
            new Select(webTimeZoneDropdown).selectByValue(profile.getTimeZone());
        }
        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_MAX_FILE_AGE_FIELD)), Integer.toString(profile.getMaxFileAge()));
        WebElement webItemsPerPageDropdown = driver.findElement(By.cssSelector(CSS_ITEMS_PER_PAGE_DROPDOWN));
        if (webItemsPerPageDropdown.isEnabled()) {
            new Select(webItemsPerPageDropdown).selectByValue(Integer.toString(profile.getItemsPerPage()));
        }
    }

    public void tryEditFirstNameField(String firstName) {

        ControlsHelper.fillTextBox(driver.findElement(By.cssSelector(CSS_FIRST_NAME_FIELD)), firstName);
        driver.findElement(By.cssSelector(CSS_SAVE_CHANGES_BUTTON)).click();
    }

    public String getErrorMessage() {

        return driver.findElement(By.cssSelector(CSS_ERROR_MESSAGE_BOX)).getText();
    }
}
