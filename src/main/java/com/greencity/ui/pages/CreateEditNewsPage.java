package com.greencity.ui.pages;

import com.greencity.ui.components.baseComponents.CancelConfirmModal;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class CreateEditNewsPage extends BasePage {

    public CreateEditNewsPage(WebDriver driver) {
        super(driver);
    }

    private static final By CANCEL_CONFIRM_MODAL = By.xpath("//app-warning-pop-up");

    @FindBy(css = "textarea[formcontrolname='title']")
    private WebElement titleInput;

    @FindBy(css = "input[formcontrolname='source']")
    private WebElement sourceInput;

    @FindBy(xpath = "//input[@id='upload']")
    private WebElement browserLink;

    @FindBy(xpath = "//button[@class='primary-global-button s-btn']")
    private WebElement submitImgButton;

    @FindBy(xpath = "//button[@class='secondary-global-button s-btn']")
    private WebElement cancelImgButton;

    @FindBy(xpath = "//div[contains(@class, 'ql-editor')]")
    private WebElement contentInput;

    @FindBy(xpath = "//button[@type='submit' and contains(@class,'primary-global-button')]")
    private WebElement publishButton;

    @FindBy(xpath = "//button[@class='tertiary-global-button']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[@class='secondary-global-button']")
    private WebElement previewButton;

    public void enterTitle(String title) {
        titleInput.clear();
        titleInput.sendKeys(title);
    }

    public void enterSource(String source) {
        sourceInput.clear();
        sourceInput.sendKeys(source);
    }

    public void enterContent(String content) {
        contentInput.clear();
        contentInput.sendKeys(content);
    }

    public void uploadImage(String filePath) {
        browserLink.sendKeys(filePath);
    }

    public void clickSubmitImage() {
        waitUntilElementClickable(submitImgButton);
        submitImgButton.click();
    }

    public void clickCancelImgButton() {
        waitUntilElementClickable(cancelImgButton);
        cancelImgButton.click();
    }

    public CancelConfirmModal clickCancel() {
        waitUntilElementClickable(cancelButton);
        cancelButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalRoot = wait.until(
                ExpectedConditions.visibilityOfElementLocated(CANCEL_CONFIRM_MODAL)
        );

        return new CancelConfirmModal(driver, modalRoot);
    }

    public void clickPreview() {
        waitUntilElementClickable(previewButton);
        previewButton.click();

    }

    public void clickPublish() {
        waitUntilElementClickable(publishButton);
        publishButton.click();

    }

    public boolean isFormDisplayed() {
        return titleInput.isDisplayed() && contentInput.isDisplayed();
    }

    public String getTitleText() {
        return titleInput.getAttribute("value");
    }

    public String getContentText() {
        return contentInput.getText();
    }
}
