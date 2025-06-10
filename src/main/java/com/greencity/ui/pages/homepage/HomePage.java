package com.greencity.ui.pages.homepage;

import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
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


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public EcoNewsPage clickReadNewsLink() {
        readNewsLink.click();
        return new EcoNewsPage(driver);
    }
}