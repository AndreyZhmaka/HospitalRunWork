package pages.hospitalRunPages;

import config.Project;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class MedicationEditPage extends BasePage {
    @FindBy(xpath = "//input[contains(@id,'patientTypeAhead-ember')]")
    private WebElement patientField;

    @FindBy(xpath = "//select[contains(@id,'visit-ember')]")
    private WebElement visitList;

    @FindBy(xpath = "//input[contains(@id,'inventoryItemTypeAhead-ember')]")
    private WebElement medicationField;

    @FindBy(xpath = "//div[contains(@class,'tt-dataset-1')]/div")
    private WebElement medicationNumber;

    @FindBy(className = "ember-text-area")
    public WebElement prescriptionField;

    @FindBy(xpath = "//input[contains(@id,'display_prescriptionDate')]")
    private WebElement dataField;

    @FindBy(xpath = "//input[contains(@id, 'quantity-ember')]")
    public WebElement quantityRequestField;

    @FindBy(xpath = "//input[contains(@id, 'refills-ember')]")
    public WebElement refillsField;

    @FindBy(xpath = "//button[text() = 'Add']")
    public WebElement addRequestButton;

    @FindBy(className = "modal-title")
    private WebElement modalTitle;

    @FindBy(className = "octicon-x")
    public WebElement closeModalButton;

    @FindBy(xpath = "//button[text() = 'Ok']")
    public WebElement submitModalButton;

    private By dateLocator = By.xpath("//option[contains(@value,'hospitalrun')]");
    private By modalTitleLocator = By.className("modal-title");

    public MedicationEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Select patient name")
    public void fillPatientField(String userName, String userFromAvailableList) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        patientField.sendKeys(userName);
        String userNameForXpath = "'" + userName + "'";
        String userNameFromListForXpath = "'" + userFromAvailableList + "'";

        getWait().until((driver) -> {
            for (int i = 0; i < 23; i ++) {
                List<WebElement> listOfPatients = getDriver().findElements(By.xpath("//pre[text()=" + userNameForXpath + "]"));
                if (listOfPatients.size() > 0) {
                    getDriver().findElement(By.xpath("//div[text() = " + userNameFromListForXpath + "]")).click();
                    return true;
                } else {
                    patientField.sendKeys(Keys.SPACE);
                    patientField.sendKeys(Keys.BACK_SPACE);
                }
            }
            return new NoSuchElementException("element not found");
        });
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Project.config.implicitlyWait()));
    }

    @Step("Select any free date for visit")
    public void selectAnyFreeDateForVisit() {
        Select select = new Select(visitList);
        select.selectByIndex(0);
        getDriver().findElement(dateLocator).isEnabled();
        select.selectByIndex(1);
    }

    @Step("Select medication {nameOfMedication} for patient")
    public void fillMedicationField(String nameOfMedication) {
        medicationField.sendKeys(nameOfMedication);
        medicationNumber.click();
    }

    @Step("Select prescription date {localDate}")
    public void selectPrescriptionData(LocalDate localDate) {
        String date = String.format("%s/%s/%s", localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getYear());
        dataField.clear();
        dataField.sendKeys(date);
        dataField.sendKeys(Keys.ENTER);
    }

    public Boolean waitForTextInModalTitle(String titleText) {
        return getWait().until(ExpectedConditions.textToBe(modalTitleLocator, titleText));
    }

    public Boolean isModalNotPresent() {
        List<WebElement> list = getDriver().findElements(modalTitleLocator);
        return list.size() == 0;
    }
}
