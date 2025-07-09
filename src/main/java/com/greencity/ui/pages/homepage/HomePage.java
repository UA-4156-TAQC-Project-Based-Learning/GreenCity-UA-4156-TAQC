package com.greencity.ui.pages.homepage;

import com.greencity.ui.components.homePageComponents.statsSection.StatsSectionComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class HomePage extends BasePage {

    @FindBy(xpath = "//section[@id='events']")
    private WebElement ecoNewsSection;

    @FindBy(xpath = "//section[@id='events']/h2[@class='section-caption']")
    private WebElement ecoNewsSectionTitle;

    @FindBy(xpath = "//a[@aria-label='link to eco-news page']")
    private WebElement readNewsLink;

    @FindBy(xpath = "//section[contains(@id, 'stats')]")
    private WebElement statisticsSection;

    @FindBy(css = "header#header h1")
    private WebElement advertisingTitle;

    @FindBy(css = "header#header p")
    private WebElement advertisingText;

    @FindBy(css = "header#header button.primary-global-button")
    private WebElement startFormingHabitButton;

    @FindBy(id = "guy-image")
    private WebElement advertisingImage;

    @FindBy(xpath = "//app-subscribe")
    private WebElement sectionSubscription;

    @FindBy(xpath = "//app-subscribe//h2")
    private WebElement sectionSubscriptionTitle;

    @FindBy(xpath = "//app-subscribe//img")
    private WebElement sectionSubscriptionQRImg;

    @FindBy(xpath = "//app-subscribe//div[contains(@id, 'form-wrapper')]/p")
    private WebElement sectionSubscriptionDescription;

    @FindBy(xpath = "//app-subscribe//div[contains(@class, 'form-input')]/input")
    private WebElement sectionSubscriptionEmailInput;

    @FindBy(xpath = "//app-subscribe//p[contains(@id, 'validation-error')]")
    private WebElement sectionSubscriptionValidationEmailError;

    @FindBy(xpath = "//app-subscribe//button")
    private WebElement sectionSubscriptionSubmitButton;

    private final By sectionSubscriptionSuccessMessageBy = By.xpath("//mat-snack-bar-container//div[contains(@class, 'mdc-snackbar__label')]");
    private WebElement sectionSubscriptionSuccessMessage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public EcoNewsPage clickReadNewsLink() {
        readNewsLink.click();
        return new EcoNewsPage(driver);
    }

    @Step("Scroll to statistics Section and navigate to Stats Section Component")
    public StatsSectionComponent goToStatisticsSection(){
        this.scrollToElement(statisticsSection);
        waitUntilElementVisible(statisticsSection);
        return new StatsSectionComponent(driver, statisticsSection);
    }

    @Step("Check if advertising title is displayed")
    public boolean isAdvertisingTitleDisplayed() {
        return advertisingTitle.isDisplayed();
    }

    @Step("Get advertising title text")
    public String getAdvertisingTitleText() {
        return advertisingTitle.getText().trim();
    }

    @Step("Check if advertising text is displayed")
    public boolean isAdvertisingTextDisplayed() {
        return advertisingText.isDisplayed();
    }

    public String getAdvertisingText() {
        return advertisingText.getText();
    }

    @Step("Check if advertising image is displayed")
    public boolean isAdvertisingImageDisplayed() {
        return advertisingImage.isDisplayed();
    }

    @Step("Check if 'Start forming a habit!' button is visible")
    public boolean isStartFormingHabitButtonDisplayed() {
        return startFormingHabitButton.isDisplayed();
    }

    @Step("Get 'Start forming a habit!' button text")
    public String getStartFormingHabitButtonText() {
        return startFormingHabitButton.getText().trim();
    }

    @Step("Click on 'Start forming a habit!' button")
    public void clickStartFormingHabitButton() {
        startFormingHabitButton.click();
    }

    @Step("Enter email into Section Subscription Email Input {email}")
    public HomePage enterEmailIntoSectionSubscriptionEmailInput(String email) {
        sectionSubscriptionEmailInput.clear();
        sectionSubscriptionEmailInput.sendKeys(email);
        return this;
    }

    @Step("Click Section Subscription Submit Button")
    public HomePage clickSectionSubscriptionSubmitButton(){
        sectionSubscriptionSubmitButton.click();
        if(!sectionSubscriptionValidationEmailError.isDisplayed()){
            sectionSubscriptionSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(sectionSubscriptionSuccessMessageBy));
        }
        return this;
    }
}