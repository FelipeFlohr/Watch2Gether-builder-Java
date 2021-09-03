import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        ReadTxtFile txt = new ReadTxtFile("links.txt");
        var links = txt.getVideoURLs();

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        W2GPageRelated w2gPage = new W2GPageRelated(driver, "test");
        w2gPage.generateW2GRoom();
        for(int i = 0; i < links.size(); i++){
            w2gPage.addVideo(links.get(i));
        }
    }

}
