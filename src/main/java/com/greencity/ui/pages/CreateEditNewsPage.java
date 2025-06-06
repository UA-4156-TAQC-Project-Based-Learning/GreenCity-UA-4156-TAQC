package com.greencity.ui.pages;

import com.greencity.ui.components.baseComponents.CancelConfirmModal;
import com.greencity.ui.pages.abstractNewsPage.PreviewNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CreateEditNewsPage extends BasePage {
    public CreateEditNewsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//h2[contains(@class, 'title-header')]")
    private WebElement titleHeader;

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

    @FindBy(xpath = ".//span[@class='span span-title']")
    private WebElement titleCharacterCounter;

    @FindBy(xpath = ".//p[@class='textarea-description']")
    private WebElement contentCharacterCounter;

    @FindBy(xpath = ".//div[@class='date']/p/span[contains(text(),'Date')]")
    private WebElement dateLabel;

    @FindBy(xpath = ".//div[@class='date']/p/span[contains(text(),'Author')]")
    private WebElement authorLabel;

    @FindBy(xpath = ".//div/span[@class='span']")
    private WebElement sourcePlaceholder;

    @FindBy(xpath = ".//div[@class='centered']")
    private WebElement browserLabel;

    @FindBy(xpath = "//div[@class='mdc-dialog__container']")
    private WebElement cancelConfirmModule;

    @FindBy(xpath = "//div[@class='mdc-dialog__container']")
    private WebElement modalRoot;

    private final By cropperBy = By.xpath(".//div[contains(@class, 'ngx-ic-cropper')]");

    private final By imageWarningBy = By.xpath(".//p[contains(@class, 'warning')]");

    public CreateEditNewsPage enterTitle(String title) {
        titleInput.clear();
        titleInput.sendKeys(title);
        return this;
    }

    public CreateEditNewsPage enterSource(String source) {
        sourceInput.clear();
        sourceInput.sendKeys(source);
        return this;
    }

    public CreateEditNewsPage enterContent(String content) {
        contentInput.clear();
        contentInput.sendKeys(content);
        return this;
    }

    public void uploadImage(String filePath) {
        browserLink.sendKeys(filePath);
    }

    public CreateEditNewsPage clickSubmitImage() {
        waitUntilElementClickable(submitImgButton);
        submitImgButton.click();
        return this;
    }

    public CreateEditNewsPage clickCancelImgButton() {
        waitUntilElementClickable(cancelImgButton);
        cancelImgButton.click();
        return this;
    }

    public CancelConfirmModal clickCancelButton() {
        cancelButton.click();
        return new CancelConfirmModal(driver, modalRoot );
    }

    public PreviewNewsPage clickPreview() {
        waitUntilElementClickable(previewButton);
        previewButton.click();
        return new PreviewNewsPage(driver);
    }

    public EcoNewsPage clickPublish() {
        waitUntilElementClickable(publishButton);
        publishButton.click();
        return new EcoNewsPage(driver);
    }
    public WebElement getCropper() {
        return driver.findElement(cropperBy);
    }

    public WebElement getImageWarning(){
        return driver.findElement(imageWarningBy);
    }
}
