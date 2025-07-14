package com.greencity.ui.components.event;

import com.greencity.ui.components.baseComponents.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class EventComponent extends BaseComponent {

    @FindBy(xpath = ".//p[contains(@class, 'event-name')]")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='btn-group']/button[contains(text(),'More')]")
    private WebElement moreButton;

    public EventComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitleText() {
        return title.getText().trim();
    }
}
