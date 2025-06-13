package com.greencity.ui.components.baseComponents;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInModal extends BaseComponent {

    private static final By SIGN_IN_MODAL_ROOT = By.cssSelector("app-auth-modal");

    public SignInModal(WebDriver driver) {
        super(driver, driver.findElement(SIGN_IN_MODAL_ROOT));
    }

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement signInButton;

    @FindBy(css = "#email-err-msg")
    private WebElement emailError;

    @FindBy(css = "#pass-err-msg")
    private WebElement passwordError;

    @FindBy(css = ".forgot-password")
    private WebElement forgotPasswordLink;

    @FindBy(css = "app-google-btn .google-sign-in")
    private WebElement googleSignInButton;

    @FindBy(css = ".green-link[aria-label='sign up modal window']")
    private WebElement signUpLink;

    @FindBy(css = ".mat-snack-bar-container span")
    private WebElement snackBarSuccessMessage;

    @FindBy(css = ".close-modal-window")
    private WebElement closeButton;

    public void clickSignIn() {
        if (signInButton.isDisplayed() && signInButton.isEnabled()) {
            signInButton.click();
        }
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickGoogleSignIn() {
        googleSignInButton.click();
    }

    public void clickSignUp() {
        signUpLink.click();
    }

    public void clickForgotPassword() {
        forgotPasswordLink.click();
    }

    public void close() {
        closeButton.click();
    }

    public String getEmailError() {
        return emailError.getText().trim();
    }

    public String getPasswordError() {
        return passwordError.getText().trim();
    }

    public String getSuccessMessage() {
        return snackBarSuccessMessage.getText().trim();
    }

    public boolean isSignInButtonEnabled() {
        return signInButton.isEnabled();
    }

    @Step("Check if Sign In modal is displayed")
    public boolean isDisplayed() {
        return emailInput.isDisplayed() && passwordInput.isDisplayed();
    }
}

