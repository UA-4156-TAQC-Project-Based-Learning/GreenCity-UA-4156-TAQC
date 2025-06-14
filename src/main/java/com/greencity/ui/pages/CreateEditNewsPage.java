package com.greencity.ui.pages;

import com.greencity.ui.components.baseComponents.CancelConfirmModal;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.abstractNewsPage.PreviewNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.Random;

@Getter
public class CreateEditNewsPage extends BasePage {
    @FindBy(xpath = "//h2[contains(@class, 'title-header')]")
    private WebElement titleHeader;

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

    @FindBy(xpath = "//div[contains(@class, 'ql-editor')]/p")
    private WebElement contentInput;

    @FindBy(xpath = "//button[@type='submit' and contains(@class,'primary-global-button')]")
    private WebElement publishButton;

    @FindBy(xpath = "//button[@class='tertiary-global-button']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[@class='secondary-global-button']")
    private WebElement previewButton;

    @FindBy(xpath = "//span[@class='span span-title']")
    private WebElement titleCharacterCounter;

    @FindBy(xpath = "//p[@class='textarea-description']")
    private WebElement contentCharacterCounter;

    @FindBy(xpath = "//div[@class='date']/p/span[contains(text(),'Date')]")
    private WebElement dateLabel;

    @FindBy(xpath = "//div[@class='date']/p/span[contains(text(),'Author')]")
    private WebElement authorLabel;

    @FindBy(xpath = "//div/span[@class='span']")
    private WebElement sourcePlaceholder;

    @FindBy(xpath = "//div[@class='centered']")
    private WebElement browserLabel;

    @FindBy(xpath = "//button[@class='primary-global-button']")
    private WebElement editButton;

    @FindBy(xpath = "//div[@class='mdc-dialog__container']")
    private WebElement cancelConfirmModule;

    @FindBy(xpath = "//div[@class='mdc-dialog__container']")
    private WebElement modalRoot;

    @FindBy(xpath = "//p[contains(@class,'field-info')]")
    private WebElement contentInfoMessage;

    @FindBy(xpath = "//span[@class = 'span field-info warning']")
    private WebElement sourceErrorMessage;

    @FindBy(xpath = "//img[contains(@class, 'ngx-ic-source-image')]")
    private WebElement imagePresentation;

    @FindBy(xpath = "//p[contains(@class, 'warning')]")
    private WebElement imageWarning;

    @FindBy(xpath = "//p[@class='textarea-description warning']")
    private WebElement descriptionWarningTextarea;

    private final By titleWarningCounterBy=By.xpath("//div[@class='title-wrapper']/span[contains(@class,'field-info')]");

    public CreateEditNewsPage(WebDriver driver) {
        super(driver);
    }

    public static String generateText(int length) {

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }

    public boolean isAuthorEditable() {
        return "true".equals(getAuthorLabel().getAttribute("contenteditable"));
    }

    public boolean isDateEditable() {
        return "true".equals(getDateLabel().getAttribute("contenteditable"));
    }

    public boolean isTitleFieldHighlightedInRed() {
        String classAttr = titleInput.getAttribute("class");
        return classAttr.contains("ng-invalid");
    }

    public boolean isStillOnEditPage() {
        return driver.getCurrentUrl().contains("#/news/create-news");
    }

    @Step("Enter {title}")
    public CreateEditNewsPage enterTitle(String title) {
        titleInput.clear();
        titleInput.sendKeys(title);
        return this;
    }

    @Step("Enter {source}")
    public CreateEditNewsPage enterSource(String source) {
        sourceInput.clear();
        sourceInput.sendKeys(source);
        return this;
    }

    @Step("Enter Content 20 digits text")
    public CreateEditNewsPage enterContent(String content) {
        contentInput.clear();
        contentInput.sendKeys(content);
        return this;
    }

    public CreateEditNewsPage enterContentJS(String text) {
        contentInput.clear();
        threadJs.executeScript("arguments[0].innerText = arguments[1];"
                                    + "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                                getContentInput(),
                                text);
        return this;
    }

    public CreateEditNewsPage uploadImage(String filePath) {
        browserLink.sendKeys(filePath);
        return this;
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

    @Step("Click Cancel button")
    public CancelConfirmModal clickCancelButton() {
        cancelButton.click();
        return new CancelConfirmModal(driver, modalRoot);
    }

    public PreviewNewsPage clickPreview() {
        waitUntilElementClickable(previewButton);
        previewButton.click();
        return new PreviewNewsPage(driver);
    }

    @Step("Click Publish button")
    public EcoNewsPage clickPublish() {
        waitUntilElementClickable(publishButton);
        publishButton.click();
        return new EcoNewsPage(driver);
    }

    @Step("Select {tag}")
    public CreateEditNewsPage clickTag(NewsTags tag) {
        driver.findElement(tag.getLocator()).click();
        return this;
    }

    public EcoNewsPage createNews(String title, String source, NewsTags tag, String content) {
        return this.enterTitle(title)
                .enterSource(source)
                .clickTag(tag)
                .enterContent(content)
                .clickPublish();
    }

    public EcoNewsPage clickEdit() {
        waitUntilElementVisible(editButton);
        editButton.click();
        return new EcoNewsPage(driver);
    }

    public WebElement getTitleCounter() {

        return driver.findElement(titleWarningCounterBy);
    }

    public boolean isFormDisplayed() {
        try {
            return titleInput != null && titleInput.isDisplayed() && contentInput != null && contentInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTitleText() {
        return titleInput.getAttribute("value");
    }

    public String getContentText() {
        return contentInput.getText().trim();
    }

}
