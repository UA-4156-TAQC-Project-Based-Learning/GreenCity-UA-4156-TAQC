package com.greencity.ui.pages.events;

import com.greencity.ui.components.event.EventComponent;
import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class EventPage extends BasePage {

    @FindBy(xpath = "//p[contains(@class, 'event-name')]")
    private WebElement title;

    @FindBy(xpath = "//div[@class='event-list ng-star-inserted']/mat-card")
    private List<WebElement> eventsCard;

    @FindBy(xpath = "//app-add-comment")
    private WebElement commentsRoot;

    private List<EventComponent> event;

    public EventPage(WebDriver driver) {
        super(driver);
    }


    public List<EventComponent> getEventsComponents() {
        return eventsCard.stream().map(el -> new EventComponent(driver, el)).toList();
    }


    public EventPage findEventByTitleAndClick(String title) {
        List<EventComponent> eventList = this.getEventsComponents();
        for (EventComponent events : eventList) {
            if (events.getTitleText().equals(title)) {
                waitUntilElementClickable(events.getMoreButton());
                events.getMoreButton().click();
                break;
            }
        }
        return new EventPage(driver);
    }
}
