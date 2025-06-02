package com.greencity.ui.components.baseComponents;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CancelConfirmModal extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class,'warning-title')]")
    private WebElement titleText;

    @FindBy(xpath = "//div[contains(@class,'warning-subtitle')]")
    private WebElement subtitleText;

    @FindBy(xpath = "//button[contains(@class,'m-btn') and contains(@class,'secondary-global-button')]")
    private WebElement continueEditingButton;

    @FindBy(xpath = "button.m-btn.primary-global-button")
    private WebElement yesCancelButton;

    @FindBy(xpath = "//button[contains(@class,'close')]")
    private WebElement closeButton;

    public CancelConfirmModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickContinueEditing() {
        continueEditingButton.click();
    }

    public void clickYesCancel() {
        yesCancelButton.click();
    }

    public void closeModal() {
        closeButton.click();
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }
}
