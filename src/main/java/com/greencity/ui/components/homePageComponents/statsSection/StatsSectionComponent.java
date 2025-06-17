package com.greencity.ui.components.homePageComponents.statsSection;

import com.greencity.ui.components.baseComponents.BaseComponent;
import com.greencity.ui.components.homePageComponents.statsSection.statsSubsections.BaseStatsSubsectionComponent;
import com.greencity.ui.components.homePageComponents.statsSection.statsSubsections.StatsSectionCupsComponent;
import com.greencity.ui.components.homePageComponents.statsSection.statsSubsections.StatsSectionEcoBagsComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class StatsSectionComponent extends BaseComponent {

    @FindBy(xpath = ".//h2[contains(@class, 'section-caption')]")
    private WebElement statisticsTitle;

    @FindBy(xpath = ".//div[contains(@id, 'stat-rows')]//app-stat-row[1]")
    private WebElement ecoBagsSection;

    @FindBy(xpath = ".//div[contains(@id, 'stat-rows')]//app-stat-row[2]")
    private WebElement cupsSection;


    public StatsSectionComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Scroll to Eco Bags Section and navigate to Stats Section Eco Bags Component")
    public StatsSectionEcoBagsComponent goToEcoBagsComponent() {
        return goToSubsectionComponent(ecoBagsSection, StatsSectionEcoBagsComponent.class);
    }

    @Step("Scroll to Cups Section and navigate to Stats Section Cups Component")
    public StatsSectionCupsComponent goToCupsComponent() {
        return goToSubsectionComponent(cupsSection, StatsSectionCupsComponent.class);
    }

    private <T extends BaseStatsSubsectionComponent> T goToSubsectionComponent(WebElement element, Class<T> clazz) {
        this.scrollToElement(element);
        waitUntilElementVisible(element);

        try {
            T component = clazz.getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, element);
            waitUntilElementVisible(component.getImage());
            return component;
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate subsection component: " + clazz.getSimpleName(), e);
        }
    }
}
