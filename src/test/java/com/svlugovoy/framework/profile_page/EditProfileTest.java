package com.svlugovoy.framework.profile_page;


import com.svlugovoy.framework.BaseTest;
import com.svlugovoy.framework.dataobject.ProfileData;
import com.svlugovoy.framework.pageobject.page.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

public class EditProfileTest extends BaseTest {

    @Test(dataProvider = "profile")
    public void canEditProfileWithValidValues(ProfileData profile) {

        ProfilePage profilePage = homePage.getMenuNavigation().selectProfilePage();

        profilePage.editProfile(profile);

        profilePage = homePage.getMenuNavigation().selectProfilePage();
        ProfileData savedProfile = profilePage.getProfileData();

        Assert.assertEquals(savedProfile, profile);

        String titleFullName = homePage.getTopMenuBlock().getFullName();
        String expectedFullName = profile.getFirstName() + " " + profile.getLastName();
        Assert.assertTrue(titleFullName.equalsIgnoreCase(expectedFullName));
    }

    @DataProvider
    public Object[][] profile() {
        return new Object[][]{
                {new ProfileData(
                        randomAlphabetic(2),
                        randomAlphabetic(2),
                        randomAlphabetic(2),
                        "http://www.blabla.com",
                        randomAlphabetic(3),
                        randomNumeric(7),
                        "Europe/Kiev",
                        50,
                        365)}
        };
    }

    @Test
    public void canNotEditProfileWithIncorrectFirstName(){

        ProfilePage profilePage = homePage.getMenuNavigation().selectProfilePage();
        profilePage.tryEditFirstNameField("");

        Assert.assertEquals(profilePage.getErrorMessage(),
                "First Name must have length from 2 to 30 and may contain only letters, spaces, dot and dash characters");
    }


}
