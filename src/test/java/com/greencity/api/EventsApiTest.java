package com.greencity.api;

import com.greencity.api.clients.EventClient;
import com.greencity.api.models.events.EventsPage;
import com.greencity.api.models.events.ResponseEventsPage;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.IOException;

public class EventsApiTest extends AuthorizedApiTestRunner {
    private EventClient eventClient;

    @BeforeMethod
    public void initEventClient() {
        eventClient = new EventClient(testValueProvider.getBaseAPIGreencityUrl());
        eventClient.setToken(token);
    }

    @Test
    public void getAllEvents() {

        Response response = eventClient.getAllEvents(5, 0, 1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        ResponseEventsPage responseEventsPage = response.as(ResponseEventsPage.class);

        softAssert.assertNotNull(responseEventsPage, "ResponseEventsPage should not be null");
        softAssert.assertNotNull(responseEventsPage.getPage(), "Events list should not be null");
        softAssert.assertTrue(responseEventsPage.getTotalElements() >= 0, "Total elements should be >= 0");
        softAssert.assertTrue(responseEventsPage.getCurrentPage() >= 0, "Current page should be >= 0");
        softAssert.assertTrue(responseEventsPage.getTotalPages() >= 0, "Total pages should be >= 0");
        softAssert.assertTrue(responseEventsPage.getNumber() >= 0, "Number should be >= 0");

        if (!responseEventsPage.getPage().isEmpty()) {
            var event = responseEventsPage.getPage().get(0);
            softAssert.assertNotNull(event.getTitle(), "Event title should not be null");
            softAssert.assertNotNull(event.getOrganizer(), "Event organizer should not be null");
            softAssert.assertNotNull(event.getCreationDate(), "Event creationDate should not be null");
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "eventId")
    public Object[][] eventIds() {
        return new Object[][]{
                {2L}
        };
    }

    @Test(dataProvider = "eventId")
    public void getEventById(long eventId) {

        Response response = eventClient.getEventById(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        EventsPage event = response.as(EventsPage.class);

        softAssert.assertNotNull(event, "Event should not be null");
        softAssert.assertEquals(event.getId().longValue(), eventId, "Event ID should match");
        softAssert.assertNotNull(event.getTitle(), "Title should not be null");
        softAssert.assertNotNull(event.getOrganizer(), "Organizer should not be null");
        softAssert.assertNotNull(event.getCreationDate(), "Creation date should not be null");
        softAssert.assertNotNull(event.getDates(), "Dates should not be null");
        softAssert.assertFalse(event.getDates().isEmpty(), "Dates list should not be empty");
        softAssert.assertNotNull(event.getTags(), "Tags should not be null");
        softAssert.assertAll();
    }

    @Test
    public void createEventWithJsonFile() throws IOException {
        File jsonFile = new File("src/test/resources/eventFile/event.json");

        Response response = eventClient.createEvent(jsonFile);

        SoftAssert softAssert = new SoftAssert();

        int statusCode = response.getStatusCode();
        softAssert.assertEquals(statusCode, 201, "Status code should be 201");

        if (statusCode == 201) {
            EventsPage createdEvent = response.as(EventsPage.class);

            softAssert.assertNotNull(createdEvent.getId(), "Created event ID should not be null");
            softAssert.assertEquals(createdEvent.getTitle(), "New Event", "Title should match");
            softAssert.assertEquals(createdEvent.getDescription(), "New Test Event", "Description should match");
            softAssert.assertTrue(createdEvent.isOpen(), "Event should be open");
        }

        softAssert.assertAll();
    }

    @Test(dataProvider = "eventId")
    public void likeEventTest(long eventId) {

        Response response = eventClient.likeEvent(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertAll();
    }

    @Test
    public void updateEventWithJsonFile() {
        int eventId = 10;
        File jsonFile = new File("src/test/resources/eventFile/update_event.json");

        Response beforeUpdate = eventClient.getEventById(eventId);
        EventsPage eventBefore = beforeUpdate.as(EventsPage.class);

        Response response = eventClient.updateEvent(eventId, jsonFile);
        String rawBody = response.asString();

        SoftAssert softAssert = new SoftAssert();

        int statusCode = response.getStatusCode();
        softAssert.assertEquals(statusCode, 200, "Status code should be 200");

        if (statusCode != 200) {
            softAssert.fail("Event was not updated successfully. Check response: " + rawBody);
            softAssert.assertAll();
            return;
        }

        EventsPage eventAfter = response.as(EventsPage.class);

        softAssert.assertNotEquals(eventAfter.getTitle(), eventBefore.getTitle(), "Title should be updated");
        softAssert.assertNotEquals(eventAfter.getDescription(), eventBefore.getDescription(), "Description should be updated");
        softAssert.assertEquals(eventAfter.getId(), eventBefore.getId(), "Event ID should remain the same");
        softAssert.assertEquals(eventAfter.getOrganizer().getId(), eventBefore.getOrganizer().getId(), "Organizer should not change");
        softAssert.assertAll();
    }

    @Test
    public void deleteEvent() {
        long eventId = 10;

        Response deleteResponse = eventClient.deleteEvent(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(deleteResponse.getStatusCode(), 200, "Delete status should be 200");

        Response getResponse = eventClient.getEventById(eventId);
        softAssert.assertEquals(getResponse.getStatusCode(), 404, "Event should not be found after deletion");

        softAssert.assertAll();
    }

    @Test
    public void addEventToFavorites() {
        long eventId = 2;
        Response response = eventClient.addEventToFavorites(eventId);

        int statusCode = response.getStatusCode();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200, "Status code should be 200");

        String cacheControl = response.getHeader("cache-control");
        softAssert.assertNotNull(cacheControl, "cache-control header should be present");
        softAssert.assertAll();
    }

    @Test
    public void removeEventFromFavorites() {
        long eventId = 2;

        eventClient.addEventToFavorites(eventId);

        Response response = eventClient.removeEventFromFavorites(eventId);

        int statusCode = response.getStatusCode();
        System.out.println("Remove from favorites status: " + statusCode);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200, "Status code should be 200");

        Response eventResponse = eventClient.getEventById(eventId);
        EventsPage event = eventResponse.as(EventsPage.class);
        softAssert.assertFalse(event.isFavorite(), "Event should no longer be marked as favorite");

        softAssert.assertAll();
    }
}
