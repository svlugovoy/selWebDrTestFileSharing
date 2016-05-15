package com.svlugovoy.framework.home_page;

import com.svlugovoy.framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 15.05.2016.
 */
public class HomePageTest extends BaseTest {

    private static final String COMMENT_ADDED_MESSAGE = "Added comment";
    private static final String FILE_NAME = "links.txt";

    @Test
    public void canVerifyContentOfHomePage() {

        Set<String> expectedFolders = new TreeSet<>();
        expectedFolders.add("File Sharing");
        expectedFolders.add("Fun");
        expectedFolders.add("test");

        Set<String> actualFolders = homePage.getAvailableFolders();

        Assert.assertTrue(actualFolders.containsAll(expectedFolders));
    }

    @Test
    public void canCreateFolder(){

        String folderName = "testcreate";
        homePage.createFolder(folderName);
        homePage.refreshPage();
        Set<String> actualFolders = homePage.getAvailableFolders();

        Assert.assertTrue(actualFolders.contains(folderName));
    }

    @Test
    public void canRemoveFolder(){

        String folderName = "testremove";
        homePage.createFolder(folderName);
        homePage.refreshPage();
        Set<String> actualFolders = homePage.getAvailableFolders();
        if (actualFolders.contains(folderName)){
            homePage.removeFolder(folderName);
        } else {
            throw new IllegalArgumentException("removing folder does not exist.");
        }
        homePage.refreshPage();
        actualFolders = homePage.getAvailableFolders();

        Assert.assertTrue(!actualFolders.contains(folderName));
    }

    @Test
    public void canAddCommentToFile(){

        homePage.addCommentForFile(FILE_NAME, "Comment text");
        String actualMessage = homePage.getCommentMessageForFile(FILE_NAME);

        Assert.assertEquals(actualMessage, COMMENT_ADDED_MESSAGE);
    }
}
