package browsers;

import config.Project;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HospitalRun;

import java.time.Duration;

public class Browser {
    private WebDriver driver;
    private HospitalRun hospitalRun;

    public HospitalRun hospitalRun() {
        if (this.hospitalRun == null) {
            this.hospitalRun = new HospitalRun(this.driver);
        }
        return this.hospitalRun;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void open(String browser) {
        if (Project.config.browser().equals("chrome") || browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);
        } else if (Project.config.browser().equals("firefox") || browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().setSize(new Dimension(Project.config.browserWidth(), Project.config.browserHeight()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Project.config.pageLoadTimeOut()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Project.config.implicitlyWait()));
    }

    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    public void close() {
        driver.quit();
    }
}
