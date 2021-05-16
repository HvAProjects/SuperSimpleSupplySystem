package selenium.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class WebDriverUtils {

    public static WebDriver createWebDriver() {
//        ClassLoader classLoader = WebDriverUtils.class.getClassLoader();
//        String fileUrl = Objects.requireNonNull(classLoader.getResource("barcode.y4m")).getFile();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
//        options.addArguments("--headless");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--allow-file-access");
        options.addArguments("--use-fake-device-for-media-stream");
//        options.addArguments("--use-file-for-fake-video-capture=\"" + fileUrl + "\"");
        options.addArguments("--use-file-for-fake-video-capture=\"barcode.y4m\"");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        return new ChromeDriver(options);
    }
}
