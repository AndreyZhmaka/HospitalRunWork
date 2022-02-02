package tests;

import browsers.Browser;
import helpers.ListenerTestNg;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(ListenerTestNg.class)
public abstract class BaseTest {
    protected Browser browser = new Browser();

    @BeforeSuite
    public void openBrowser(ITestContext context) {
        browser.open("chrome");
        context.setAttribute("browser", browser);
    }

    @AfterMethod
    public void deleteCookies() {
        browser.deleteCookies();
    }

    @AfterSuite
    public void closeBrowser() {
        browser.close();
    }
}
