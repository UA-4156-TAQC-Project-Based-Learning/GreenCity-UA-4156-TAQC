package com.greencity.ui.pages.homepage;

import com.greencity.ui.components.homePageComponents.statsSection.StatsSectionComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
}