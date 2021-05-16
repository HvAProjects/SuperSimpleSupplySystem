package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.util.WebDriverUtils;

public class TempMailDriver {

    private String mailAddress = null;
    private WebDriver webDriver;

    public TempMailDriver() {
        this.webDriver = WebDriverUtils.createWebDriver();
        this.webDriver.get("https://mail.tm/nl/");
    }

    public String getEmailAddress() throws Exception {
        if (mailAddress == null) {
            String tempMail = "";

            int i = 0;
            while (tempMail.equals("")) {
                if (i >= 5) throw new Exception("Failed to get temp email");
                Thread.sleep(1000);
                tempMail = this.webDriver.findElement(By.id("address")).getAttribute("value");
                i++;
            }
            mailAddress = tempMail;
        }

        return mailAddress;
    }
}
