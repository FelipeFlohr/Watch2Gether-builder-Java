package com.felipeflohr.w2gbuilder.seleniumbuilder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

public class Builder {

    private final File txtLinkFiles;
    private final String username;
    private final boolean notWorkingVideosEnabled;
    private final File notWorkingVideosFile;
    private final WebDriver driver;

    public Builder(File txtLinkFiles, String username, boolean notWorkingVideosEnabled, File notWorkingVideosFile) throws FileNotFoundException, InterruptedException {
        this.txtLinkFiles = txtLinkFiles;
        this.username = username;
        this.notWorkingVideosEnabled = notWorkingVideosEnabled;
        this.notWorkingVideosFile = notWorkingVideosFile;

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--mute-audio");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);

        buildW2G();
    }

    private void buildW2G() throws InterruptedException, FileNotFoundException {
        generateW2GRoom();
        getVideoURLs().forEach(video -> {
            try {
                addVideo(video);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void generateW2GRoom() throws InterruptedException {
        driver.get("https://w2g.tv/?lang=pt");
        driver.manage().window().maximize();

        // Will click to create a room
        WebElement createRoomBtn = driver.findElement(new By.ByXPath("//*[@id=\"create_room_button\"]"));
        createRoomBtn.click();

        // Will enter the username
        WebElement usernameInput = driver.findElement(new By.ByXPath("//*[@id=\"intro-nickname\"]"));
        usernameInput.clear();
        usernameInput.sendKeys(this.username); // Sets the username to the chosen one

        // Will click the "Enter room" button
        WebElement enterRoomBtn = driver.findElement(new By.ByXPath("//*[@id=\"intro-modal\"]/div[2]/div"));
        enterRoomBtn.click();
        Thread.sleep(3000);
    }

    private void addVideo(String url) throws IOException {
        // Selects the video search bar and inserts the video URL
        WebElement videoSearchBar = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"search-bar-input\"]")));
        videoSearchBar.clear();
        videoSearchBar.sendKeys(url);

        // Clicks the button "search"
        WebElement searchBtn = this.driver.findElement(By.xpath("//*[@id=\"search-bar-form\"]/div/button"));
        searchBtn.click();

        // Clicks to add the video
        try{
            WebElement videoAddBtn = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"w2g-search-results\"]/div[4]/div/div[3]/div[2]")));
            videoAddBtn.click();
        }
        catch(TimeoutException e){
            if (notWorkingVideosEnabled) {
                FileWriter writer = new FileWriter(notWorkingVideosFile.getAbsolutePath(), true);
                writer.write("\n" + url);
                writer.close();
            }
        }
    }

    private ArrayList<String> getVideoURLs() throws FileNotFoundException {
        final ArrayList<String> links = new ArrayList<>();

        Scanner scanner = new Scanner(txtLinkFiles);

        while (scanner.hasNext()) {
            links.add(scanner.nextLine());
        }
        scanner.close();
        return links;
    }
}