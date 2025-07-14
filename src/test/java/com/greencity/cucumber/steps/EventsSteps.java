package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.pages.events.EventPage;
import io.cucumber.java.en.When;

public class EventsSteps {

    private final Hooks hooks;


    public EventsSteps(Hooks hooks) {
        this.hooks = hooks;
    }
    public EventPage getEventPage(){
        return new EventPage(hooks.getDriver());
    }

    @When("click on event with title {string}")
    public void clickEventWithTitle(String title) {
        getEventPage().findEventByTitleAndClick(title);
    }

    @When("open the Events page")
    public void openEventPage(){
        hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl() + "/events");
    }
}
