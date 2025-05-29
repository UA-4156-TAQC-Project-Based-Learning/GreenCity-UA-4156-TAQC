package com.greencity.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CreateEditNewsPage extends BasePage {
    public CreateEditNewsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label/textarea")
    private WebElement titleInput;

    @FindBy(xpath = "//input[@placeholder='Посилання на зовнішнє джерело']")
    private WebElement sourceInput;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement browserLink;

    @FindBy(xpath = "//button[@class='primary-global-button s-btn']")
    private WebElement submitImgButton;

    @FindBy(xpath = "//button[@class='secondary-global-button s-btn']")
    private WebElement cancelImgButton;

    @FindBy(xpath = "//div[contains(@class, 'ql-editor')]")
    private WebElement contentInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement publishButton;

    @FindBy(xpath = ".//button[@class='tertiary-global-button']")
    private WebElement cancelButton;

    @FindBy(xpath = ".//button[@class='secondary-global-button']")
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
        contentInput.sendKeys(content);
    }

    public void clickPreview(){
        waitUntilElementClickable(previewButton);
        previewButton.click();

    }

    public void clickPublish() {
        waitUntilElementClickable(publishButton);
        publishButton.click();

    }
}
