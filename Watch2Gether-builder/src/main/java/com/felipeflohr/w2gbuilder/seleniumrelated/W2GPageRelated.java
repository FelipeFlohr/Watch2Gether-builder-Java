package com.felipeflohr.w2gbuilder.seleniumrelated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class W2GPageRelated {

    WebDriver driver;
    String username;

    public W2GPageRelated(WebDriver driver, String username){
        this.driver = driver;
        this.username = username;
    }

    public void generateW2GRoom() throws InterruptedException {
        this.driver.get("https://w2g.tv/?lang=pt");
        this.driver.manage().window().maximize();

        // Will click to create a room
        var createRoomBtn = this.driver.findElement(new By.ByXPath("//*[@id=\"create_room_button\"]"));
        createRoomBtn.click();

        // Will enter the username
        var usernameInput = this.driver.findElement(new By.ByXPath("//*[@id=\"intro-nickname\"]"));
        usernameInput.clear();
        usernameInput.sendKeys(this.username); // Sets the username to the chosen one

        // Will click the "Enter room" button
        var enterRoomBtn = this.driver.findElement(new By.ByXPath("//*[@id=\"intro-modal\"]/div[2]/div"));
        enterRoomBtn.click();
        Thread.sleep(3000);
    }

    public void addVideo(String url){
        // Selects the video search bar and inserts the video URL
        var videoSearchBar = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                                        .until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("//*[@id=\"search-bar-input\"]")));
        videoSearchBar.clear();
        videoSearchBar.sendKeys(url);

        // Clicks the button "search"
        var searchBtn = this.driver.findElement(By.xpath("//*[@id=\"search-bar-form\"]/div/button"));
        searchBtn.click();

        // Clicks to add the video
        try{
            var videoAddBtn = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                                          .until(ExpectedConditions.elementToBeClickable(
                                          By.xpath("//*[@id=\"w2g-search-results\"]/div[4]/div/div[3]/div[2]")));
            videoAddBtn.click();
        }
        catch(org.openqa.selenium.TimeoutException e){
            String message = "Video "+url+" not found.";
            JOptionPane.showMessageDialog(null, message, "Video not found", JOptionPane.ERROR_MESSAGE);
        }

    }

}
