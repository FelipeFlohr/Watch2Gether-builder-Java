package seleniumrelated;

import guirelated.Frame;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import seleniumrelated.ReadTxtFile;
import seleniumrelated.W2GPageRelated;

import java.io.File;
import java.io.IOException;

public class BuildW2G {

    File txtFile;
    String username;

    public BuildW2G(File txtFile, String username){
        this.txtFile = txtFile;
        this.username = username;
    }

    public void build() throws IOException, InterruptedException {
        ReadTxtFile txt = new ReadTxtFile(txtFile.getAbsolutePath());
        var links = txt.getVideoURLs();

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        W2GPageRelated w2gPage = new W2GPageRelated(driver, username);
        w2gPage.generateW2GRoom();
        for(int i = 0; i < links.size(); i++){
            w2gPage.addVideo(links.get(i));
        }
    }

}
