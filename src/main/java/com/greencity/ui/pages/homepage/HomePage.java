package com.greencity.ui.pages.homepage;

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

    @FindBy(xpath = "//app-subscribe")
    private WebElement sectionSubscription;

    @FindBy(xpath = "//app-subscribe//h2")
    private WebElement sectionSubscriptionTittle;

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

    private By sectionSubscriptionSuccessMessageBy = By.xpath("//mat-snack-bar-container//div[contains(@class, 'mdc-snackbar__label')]");
    private WebElement sectionSubscriptionSuccessMessage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public EcoNewsPage clickReadNewsLink() {
        readNewsLink.click();
        return new EcoNewsPage(driver);
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
        sectionSubscriptionSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(sectionSubscriptionSuccessMessageBy));
        return this;
    }
}