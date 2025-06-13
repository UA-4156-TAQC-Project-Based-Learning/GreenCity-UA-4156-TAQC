package com.greencity.ui.components.baseComponents;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class CancelConfirmModal extends BaseComponent {

    @FindBy(xpath = ".//div[contains(@class,'warning-title')]")
    private WebElement titleText;

    @FindBy(xpath = ".//div[contains(@class,'warning-subtitle')]")
    private WebElement subtitleText;

    @FindBy(xpath = "//button[contains(@class,'m-btn') and contains(@class,'secondary-global-button')]")
    private WebElement continueEditingButton;

    @FindBy(xpath = "//button[@class='m-btn primary-global-button']")
    private WebElement yesCancelButton;

    @FindBy(xpath = "//button[@data-testid='modal-close' or contains(@class,'close')]")
    private WebElement closeButton;

    public CancelConfirmModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickContinueEditing() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(continueEditingButton));
            continueEditingButton.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click continue editing button", e);
        }
    }

    @Step("Confirm cancel")
    public void clickYesCancel() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(yesCancelButton));
                yesCancelButton.click();
            } catch (Exception e) {
                throw new RuntimeException("Failed to click yes cancel button", e);
            }
    }

    public void closeModal() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(closeButton));
            closeButton.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click close button", e);
        }
    }

    public boolean isDisplayed() {
        try {
            return rootElement != null && rootElement.isDisplayed();
        } catch (Exception e) {
            return false; }
    }
}
