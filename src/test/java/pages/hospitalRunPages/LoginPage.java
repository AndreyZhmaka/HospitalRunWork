package pages.hospitalRunPages;

import config.Project;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "identification")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(className = "btn")
    private WebElement signInButton;

    @FindBy(className = "form-signin-heading")
    public WebElement loginFormTitle;

    private By alertNotificationLocator = By.className("alert");

    public String loginPageUrl = Project.config.baseUrl() + Project.config.loginPagePath();

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        getDriver().get(loginPageUrl);
    }

    @Step("login to Account with userName  \"{loginValue}\" and password \"{passwordValue}\"")
    public void submitLoginFormWithData(String loginValue, String passwordValue) {
        userNameField.sendKeys(loginValue);
        passwordField.sendKeys(passwordValue);
        signInButton.click();
    }

    public String getTextAlertNotification() {
        return getDriver().findElement(alertNotificationLocator).getText();
    }

    public boolean waitForPresent() {
        try {
            loginFormTitle.getText();
        } catch (NoSuchElementException a) {
            return false;
        }
        return true;
    }
}
