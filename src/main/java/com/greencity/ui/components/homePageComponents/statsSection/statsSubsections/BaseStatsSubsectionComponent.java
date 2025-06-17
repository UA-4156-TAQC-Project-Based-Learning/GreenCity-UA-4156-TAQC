package com.greencity.ui.components.homePageComponents.statsSection.statsSubsections;

import com.greencity.ui.components.baseComponents.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class BaseStatsSubsectionComponent extends BaseComponent {
    public BaseStatsSubsectionComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @FindBy(xpath = ".//div[contains(@class, 'image-wrapper')]//img")
    protected WebElement image;

    @FindBy(xpath = ".//h3")
    protected WebElement countText;

    @FindBy(xpath = ".//p")
    protected WebElement question;

    @FindBy(xpath = ".//button")
    protected WebElement button;

    @FindBy(xpath = ".//a")
    protected WebElement link;
}

