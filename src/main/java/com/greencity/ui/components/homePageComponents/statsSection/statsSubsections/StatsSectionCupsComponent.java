package com.greencity.ui.components.homePageComponents.statsSection.statsSubsections;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public class StatsSectionCupsComponent extends BaseStatsSubsectionComponent {

    public StatsSectionCupsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}
