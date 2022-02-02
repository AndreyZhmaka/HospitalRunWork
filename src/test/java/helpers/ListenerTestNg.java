package helpers;

import browsers.Browser;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTestNg implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Browser browser = (Browser) result.getTestContext().getAttribute("browser");
        AllureHelper allureHelper = new AllureHelper(browser);
        allureHelper.addScreenshotAs("Failed test screenshot");
        allureHelper.addPageSource();
    }
}
