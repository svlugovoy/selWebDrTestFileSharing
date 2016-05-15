package com.svlugovoy.framework.pageobject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Sergey Lugovoy <svlugovoy@gmail.com> 08.05.2016.
 */

public class HomePage extends BasePage {

    static final String ID_UPLOAD_BOX = "uploadBox";
    private static final String CSS_AJAX_PROGRESS = "img[src='/images/biz/ajax-progress2.gif']";
    private static final String CSS_LISTING_LOADED_ELEMENT = "#listing_loaded";
    private static final String CSS_FILE_LISTING_AREA = "#bizFileListing";
    private static final String CSS_FOLDER = ".dirEntry";
    private static final String CSS_FILE_ENTRY_TEMPLATE = ".fileEntry[bdg-name='%1$s']";
    private static final String CSS_COMMENTS_BUTTON = ".fileCommentsButton a";
    private static final String CSS_COMMENTS_INPUT = ".openComments input[type='text']";
    private static final String CSS_ADD_COMMENT_BUTTON = ".commentAddButton";
    private static final String CSS_COMMENT_ADDED_MESSAGE = ".openComments .bizOk";

    public HomePage() {
        //wait until content is loaded
        waitUntilContentIsLoaded();
    }

    public void waitUntilContentIsLoaded() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(CSS_AJAX_PROGRESS)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(CSS_LISTING_LOADED_ELEMENT)));
    }

    public Set<String> getAvailableFolders() {
        //get Set of actual folders
        List<WebElement> foldersList = driver.findElements(By.cssSelector(CSS_FILE_LISTING_AREA + " " + CSS_FOLDER));
        return foldersList.stream()
                .map(element -> element.getAttribute("bdg-name"))
                .collect(Collectors.toSet());
    }


    public void createFolder(String folderName){

        driver.findElement(By.cssSelector("#createFolderBtn")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[id='folder_name']")));
        driver.findElement(By.cssSelector("input[id='folder_name']")).sendKeys(folderName);
        driver.findElement(By.cssSelector("a[onclick='createFolderWindowed(); return false;']")).click();
        driver.findElement(By.cssSelector(".closeButton.biz_alert_close")).click();
    }

    public void removeFolder(String folderName){

        driver.findElement(By.cssSelector("li[title='" + folderName + "'] div.dirActionButton")).click();
        driver.findElement(By.cssSelector("a[title='Remove Folder']")).click();
        driver.findElement(By.cssSelector("a[onclick='window.removeFolderFormFunc.confirmRemoveFolder()'] div.bizButton")).click();
        driver.findElement(By.cssSelector(".closeButton.biz_alert_close")).click();

    }

    public void addCommentForFile(String filename, String comment) {
        WebElement fileEntry = driver.findElement(By.cssSelector(String.format(CSS_FILE_ENTRY_TEMPLATE, filename)));
        fileEntry.findElement(By.cssSelector(CSS_COMMENTS_BUTTON)).click();

        WebElement commentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_COMMENTS_INPUT)));
        commentInput.sendKeys(comment);

        fileEntry.findElement(By.cssSelector(CSS_ADD_COMMENT_BUTTON)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_COMMENT_ADDED_MESSAGE)));
    }

    public String getCommentMessageForFile(String filename) {
        WebElement fileEntry = driver.findElement(By.cssSelector(String.format(CSS_FILE_ENTRY_TEMPLATE, filename)));
        return fileEntry.findElement(By.cssSelector(CSS_COMMENT_ADDED_MESSAGE)).getText();
    }

}
