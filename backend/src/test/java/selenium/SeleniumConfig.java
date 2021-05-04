package selenium;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

class SeleniumConfig {

    private WebDriver driver;

    public SeleniumConfig() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    static {
        System.setProperty("webdriver.chrome.driver", findFile("geckodriver.mac"));
    }

    static private String findFile(String filename) {
        String paths[] = {"", "bin/", "target/classes"};
        for (String path : paths) {
            if (new File(path + filename).exists())
                return path + filename;
        }
        return "";
    }

    public WebDriver getDriver() {
        return driver;
    }
}
