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
}