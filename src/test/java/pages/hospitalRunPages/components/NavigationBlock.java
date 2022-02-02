package pages.hospitalRunPages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class NavigationBlock extends BasePage {
    @FindBy(className = "settings-trigger")
    private WebElement settingsButton;

    @FindBy(className = "logout")
    private WebElement logOutButton;

    public NavigationBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @Step("LogOut from account")
    public void logOut() {
        settingsButton.click();
        logOutButton.click();
    }
}
