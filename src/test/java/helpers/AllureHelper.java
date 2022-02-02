package helpers;

import browsers.Browser;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

public class AllureHelper {
    Browser browser;

    public AllureHelper(Browser browser) {
        this.browser = browser;
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public byte[] addScreenshotAs(String attachName) {
        return ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public byte[] addPageSource() {
        return browser.getDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }
}
