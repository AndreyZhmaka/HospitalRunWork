package pages.hospitalRunPages;

import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedicationPage extends BasePage {
    @FindBy(xpath = "//button[text() = '+ new request']")
    public WebElement newRequestButton;

    public MedicationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
