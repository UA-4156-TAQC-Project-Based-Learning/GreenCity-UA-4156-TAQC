package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.*;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Issue;
import org.testng.asserts.SoftAssert;

public class HomePageSteps {

    private final Hooks hooks;
    private HomePage homePage;
    private SoftAssert softAssert;

    public HomePageSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
    }

    private HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(hooks.getDriver());
        }
        return homePage;
    }

    @Given("the user is not logged in to the GreenCity system")
    @Description("Ensure local storage is cleared so the user is not authenticated")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_user_is_not_logged_in_to_the_green_city_system() {
        hooks.getLocalStorageJS().clearLocalStorage();
        hooks.getDriver().navigate().refresh();
    }

    @Given("the user opens the GreenCity Main Page in a maximized browser window")
    @Description("Open GreenCity main page and maximize the browser window")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_user_opens_the_green_city_main_page_in_a_maximized_browser_window() {
        hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl());
        hooks.getDriver().manage().window().maximize();
    }

    @When("the user scrolls down to the {string} section")
    @Description("Scroll to a specific section of the GreenCity main page")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_user_scrolls_down_to_the_section(String sectionName) {
        getHomePage().scrollToElement(getHomePage().getSectionSubscription());
    }

    @Then("the section title {string} should be visible")
    @Description("Check that the section title is visible on the page")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_section_title_should_be_visible(String expectedTitle) {
        softAssert.assertTrue(getHomePage().getSectionSubscription().isDisplayed(),
                "Subscription section is not visible");
    }

    @Then("a functional QR code should be displayed")
    @Description("Ensure that the QR code image is displayed in the subscription section")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void a_functional_qr_code_should_be_displayed() {
        softAssert.assertTrue(getHomePage().getSectionSubscriptionQRImg().isDisplayed(),
                "QR image is not visible");
    }

    @Then("the text {string} should be present")
    @Description("Verify the presence of specific descriptive text in the subscription section")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_text_should_be_present(String expectedText) {
        softAssert.assertTrue(getHomePage().getSectionSubscriptionDescription().isDisplayed(),
                "Description text is not visible");
    }

    @Then("the email input field should be visible with placeholder text {string}")
    @Description("Check that the email input field is visible and has the correct placeholder")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_email_input_field_should_be_visible_with_placeholder_text(String expectedPlaceholder) {
        softAssert.assertTrue(getHomePage().getSectionSubscriptionEmailInput().isDisplayed(),
                "Email input is not visible");
        softAssert.assertEquals(getHomePage().getSectionSubscriptionEmailInput().getAttribute("placeholder"),
                expectedPlaceholder, "Placeholder text mismatch");
    }

    @When("the user enters {string} into the email input field")
    @Description("Enter an email address into the subscription input field")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_user_enters_into_the_email_input_field(String email) {
        getHomePage().enterEmailIntoSectionSubscriptionEmailInput(email);
    }

    @When("clicks the {string} button")
    @Description("Click the subscription button to submit the email")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void clicks_the_button(String buttonLabel) {
        getHomePage().clickSectionSubscriptionSubmitButton();
    }

    @Then("the system should display a validation error for invalid email")
    @Description("Check that a validation error is shown when an invalid email is submitted")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_system_should_display_a_validation_error_for_invalid_email() {
        String classAttribute = getHomePage()
                .getSectionSubscriptionValidationEmailError()
                .getAttribute("class");
        softAssert.assertEquals(classAttribute, "visible", "Expected error to be visible for invalid email");
    }

    @Then("the system should accept the email")
    @Description("Verify that the system does not show an error when a valid email is submitted")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void the_system_should_accept_the_email() {
        String classAttr = getHomePage()
                .getSectionSubscriptionValidationEmailError()
                .getAttribute("class");
        softAssert.assertEquals(classAttr, "hidden", "Expected no error for valid email");
    }

    @Then("a success message or confirmation should be shown")
    @Description("Check that the system displays a success message after a valid email is submitted")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void a_success_message_or_confirmation_should_be_shown() {
        boolean isMessageDisplayed = getHomePage().getSectionSubscriptionSuccessMessage().isDisplayed();
        softAssert.assertTrue(isMessageDisplayed, "Success message not displayed");

        String actualText = getHomePage().getSectionSubscriptionSuccessMessage().getText().trim();
        softAssert.assertEquals(actualText, "You successfully subscribed to the newsletter",
                "Success message text mismatch");
    }
}