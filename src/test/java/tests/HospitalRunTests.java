package tests;

import config.Project;
import helpers.DataProviderForLogin;
import io.qameta.allure.Feature;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import utils.RandomValues;

import java.time.LocalDate;

import static io.qameta.allure.Allure.step;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class HospitalRunTests extends BaseTest {

    @Feature("Login")
    @Test
    public void submitLoginFormWithCorrectData() {
        if (browser.hospitalRun().isUserLoggedIn) {
            browser.hospitalRun().logOut();
        }
        browser.hospitalRun().loginPage().open();
        browser.hospitalRun().loginPage().submitLoginFormWithData(Project.config.username(), Project.config.password());

        step("Assert that User is logged in and Patient Listing page is displayed", () -> {
            assertThat(browser.hospitalRun().mainPage().mainPageTitle.getText()).isEqualTo("Patient Listing");
        });
        browser.hospitalRun().isUserLoggedIn = true;
    }

    @Feature("Login")
    @Test
    public void submitLoginFormWithInCorrectData() {
        if (browser.hospitalRun().isUserLoggedIn) {
            browser.hospitalRun().logOut();
        }
        browser.hospitalRun().loginPage().open();
        browser.hospitalRun().loginPage().submitLoginFormWithData("hello", "world");

        step("Assert that User is stayed on Login Page and Error message is displayed", () -> {
            assertThat(browser.hospitalRun().loginPage().waitForPresent()).isTrue();
            assertThat(browser.hospitalRun().loginPage().getTextAlertNotification()).isEqualTo("Username or password is incorrect.");
        });
    }

    @Feature("Login")
    @Test
    public void logOutFromAccount() {
        if (!browser.hospitalRun().isUserLoggedIn) {
            browser.hospitalRun().login();
        }
        browser.hospitalRun().navigationBlock().logOut();

        step("Assert that User is logged out and Login Page is displayed", () -> {
            assertThat(browser.hospitalRun().loginPage().loginFormTitle.getText()).isEqualTo("PLEASE SIGN IN");
            assertThat(browser.hospitalRun().loginPage().waitForPresent()).isTrue();
        });
        browser.hospitalRun().isUserLoggedIn = false;
    }

    @Feature("Add new Medication")
    @Ignore
    @Test(dependsOnMethods = {"logOutFromAccount", "submitLoginFormWithInCorrectData", "submitLoginFormWithCorrectData"})
    public void addNewMedication() {
        if (!browser.hospitalRun().isUserLoggedIn) {
            browser.hospitalRun().login();
        }
        browser.hospitalRun().mainPage().medicationLink.click();

        step("Assert that Medication Section contains next 4 items:" +
                " Requests, Completed, New Request, Return Medication", () -> {
            assertThat(browser.hospitalRun().mainPage().requestsLink.isDisplayed()).isTrue();
            assertThat(browser.hospitalRun().mainPage().completedLink.isDisplayed()).isTrue();
            assertThat(browser.hospitalRun().mainPage().newRequestLink.isDisplayed()).isTrue();
            assertThat(browser.hospitalRun().mainPage().returnMedicationLink.isDisplayed()).isTrue();
        });

        browser.hospitalRun().medicationPage().newRequestButton.click();
        browser.hospitalRun().medicationEditPage().fillPatientField("Test Patient", "Test - Patient - P00201");
        browser.hospitalRun().medicationEditPage().prescriptionField.sendKeys("Testing prescription");
        browser.hospitalRun().medicationEditPage().selectPrescriptionData(LocalDate.now().minusDays(1));
        browser.hospitalRun().medicationEditPage().quantityRequestField.sendKeys(String.valueOf(RandomValues.getRandomNumber(1, 5)));
        browser.hospitalRun().medicationEditPage().refillsField.sendKeys(String.valueOf(RandomValues.getRandomNumber(5, 10)));
        browser.hospitalRun().medicationEditPage().fillMedicationField("Pramoxine");
        browser.hospitalRun().medicationEditPage().selectAnyFreeDateForVisit();
        browser.hospitalRun().medicationEditPage().addRequestButton.click();

        step("Assert that Medication Request Saved popup is displayed and" +
                "contains next items: Ok button and Cross button", () -> {
            assertThat(browser.hospitalRun().medicationEditPage().waitForTextInModalTitle("Medication Request Saved")).isTrue();
            assertThat(browser.hospitalRun().medicationEditPage().closeModalButton.isDisplayed()).isTrue();
        });

        browser.hospitalRun().medicationEditPage().submitModalButton.click();

        step("Assert that Medication Request Saved pop up isn`t displayed and" +
                "User stayed on New Medication Request Page", () -> {
            assertThat(browser.hospitalRun().medicationEditPage().isModalNotPresent()).isTrue();
        });
    }

    @Test(dataProvider = "login", dataProviderClass = DataProviderForLogin.class)
    public void dataProviderTest(String username, String password) {
        browser.hospitalRun().loginPage().open();
        browser.hospitalRun().loginPage().submitLoginFormWithData(username,password);
    }

    @Test(invocationCount = 5,threadPoolSize = 5)
    public void someTest22() throws InterruptedException {
        browser.hospitalRun().loginPage().open();
        sleep(1000);
    }
}
