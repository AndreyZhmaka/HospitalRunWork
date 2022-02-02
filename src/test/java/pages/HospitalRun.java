package pages;

import config.Project;
import org.openqa.selenium.WebDriver;
import pages.hospitalRunPages.LoginPage;
import pages.hospitalRunPages.MainPage;
import pages.hospitalRunPages.MedicationEditPage;
import pages.hospitalRunPages.MedicationPage;
import pages.hospitalRunPages.components.NavigationBlock;

public class HospitalRun {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private MedicationEditPage medicationEditPage;
    private MedicationPage medicationPage;
    private NavigationBlock navigationBlock;

    public boolean isUserLoggedIn = false;

    public HospitalRun(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        this.loginPage().open();
        this.loginPage().submitLoginFormWithData(Project.config.username(), Project.config.password());
        if(this.mainPage().waitForPresent()) {
            this.isUserLoggedIn = true;
        }
    }

    public void logOut() {
        this.navigationBlock.logOut();
        if(this.loginPage().waitForPresent()) {
            this.isUserLoggedIn = false;
        }
    }

    public LoginPage loginPage() {
        if (this.loginPage == null) {
            this.loginPage = new LoginPage(this.driver);
        }
        return this.loginPage;
    }

    public MainPage mainPage() {
        if (this.mainPage == null) {
            this.mainPage = new MainPage(this.driver);
        }
        return this.mainPage;
    }

    public MedicationEditPage medicationEditPage() {
        if (this.medicationEditPage == null) {
            this.medicationEditPage = new MedicationEditPage(this.driver);
        }
        return this.medicationEditPage;
    }

    public MedicationPage medicationPage() {
        if (this.medicationPage == null) {
            this.medicationPage = new MedicationPage(this.driver);
        }
        return this.medicationPage;
    }

    public NavigationBlock navigationBlock() {
        if (this.navigationBlock == null) {
            this.navigationBlock = new NavigationBlock(this.driver);
        }
        return this.navigationBlock;
    }
}
