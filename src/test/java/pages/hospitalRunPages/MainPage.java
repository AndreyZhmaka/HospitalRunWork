package pages.hospitalRunPages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class MainPage extends BasePage {
    @FindBy(className = "view-current-title")
    public WebElement mainPageTitle;

    @FindBy(xpath = "//a[text() = 'Medication']")
    public WebElement medicationLink;

    @FindBy(xpath = "//a[text() = 'Requests']")
    public WebElement requestsLink;

    @FindBy(xpath = "//a[text() = 'Completed']")
    public WebElement completedLink;

    @FindBy(xpath = "//a[text() = 'New Request']")
    public WebElement newRequestLink;

    @FindBy(xpath = "//a[text() = 'Return Medication']")
    public WebElement returnMedicationLink;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean waitForPresent() {
        try {
            mainPageTitle.getText();
        } catch (NoSuchElementException a) {
            return false;
        }
        return true;
    }
}
