package com.greencity.ui.components.baseComponents;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CancelConfirmModal extends BaseComponent {

    @FindBy(css = ".warning-title")
    private WebElement titleText;

    @FindBy(css = ".warning-subtitle")
    private WebElement subtitleText;

    @FindBy(css = "button.m-btn.secondary-global-button")
    private WebElement continueEditingButton;

    @FindBy(xpath = "button.m-btn.primary-global-button")
    private WebElement yesCancelButton;

    @FindBy(css = "button.close")
    private WebElement closeButton;

    public CancelConfirmModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory(rootElement), this);
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
