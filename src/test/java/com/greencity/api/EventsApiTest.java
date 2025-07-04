package com.greencity.api;

import com.greencity.api.clients.EventClient;
import com.greencity.api.models.events.EventAttender;
import com.greencity.api.models.events.EventOrganizer;
import com.greencity.api.models.events.EventsPage;
import com.greencity.api.models.events.ResponseEventsPage;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import com.greencity.jdbc.entity.eventsEntity.*;
import com.greencity.jdbc.services.eventsService.*;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Feature("Events API")

public class EventsApiTest extends AuthorizedApiTestRunner {
    private EventClient eventClient;
    private EventService eventService;
    private EventUserLikeService eventUserLikeService;
    private EventUserDislikeService eventUserDislikeService;
    private EventGradeService eventGradeService;
    private EventAttenderService eventAttenderService;
    private EventRequesterService eventRequesterService;
    private EventDateLocationService eventDateLocationService;

    @BeforeClass
    public void init() {
        this.eventService = new EventService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
        this.eventUserLikeService = new EventUserLikeService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
        this.eventUserDislikeService = new EventUserDislikeService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
        this.eventGradeService = new EventGradeService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
        this.eventAttenderService = new EventAttenderService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
        this.eventRequesterService = new EventRequesterService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
        this.eventDateLocationService = new EventDateLocationService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
    }

    @BeforeMethod
    public void initEventClient() {
        eventClient = new EventClient(testValueProvider.getBaseAPIGreencityUrl());
        eventClient.setSignIn(signIn);
    }

    @Test
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify retrieving paginated list of all events for user")
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

            List<EventEntity> dbEvents = eventService.getAllEvents();
            List<Long> dbEventIds = dbEvents.stream()
                    .map(EventEntity::getId)
                    .collect(Collectors.toList());

            System.out.println("DataBase contains event IDs: " + dbEventIds);
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
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify retrieving event details by event ID")
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

        EventEntity dbEvent = eventService.getEventById(eventId);
        softAssert.assertNotNull(dbEvent, "Event should exist in Database for ID " + eventId);

        if (dbEvent != null) {
            softAssert.assertEquals(dbEvent.getId().longValue(), eventId, "Event ID from DB should match requested ID");
            softAssert.assertEquals(dbEvent.getTitle(), event.getTitle(), "Event title from API should match DB");
            softAssert.assertEquals(dbEvent.getDescription(), event.getDescription(), "Event description from API should match DB");
            softAssert.assertEquals(dbEvent.getOrganizerId(), event.getOrganizer().getId(), "Event organizer ID from API should match DB");
            softAssert.assertEquals(dbEvent.getCreationDate(), Date.valueOf(event.getCreationDate()), "Event creation date from API should match DB");
        }
        softAssert.assertAll();
    }

    @Test
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify creating a new event with JSON file")
    public void createEventWithJsonFile() {
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

            EventEntity eventFromDb = eventService.getEventById(createdEvent.getId());

            softAssert.assertNotNull(eventFromDb, "Event should be saved in DB");
            EventOrganizer organizerFromApi = createdEvent.getOrganizer();
            softAssert.assertNotNull(organizerFromApi, "Organizer in response should not be null");
            softAssert.assertNotNull(organizerFromApi.getId(), "Organizer ID in response should not be null");

            softAssert.assertEquals(
                    eventFromDb.getOrganizerId(),
                    organizerFromApi.getId(),
                    "DB: Organizer ID should match API"
            );
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "likeEventIds")
    public Object[][] likeEventIds() {
        return new Object[][]{
                {2L, 200, null},
                {5L, 404, "Event doesn't exist by this id: 5"},
                {4L, 400, "Current user has no permission for this action"}
        };
    }

    @Test(dataProvider = "likeEventIds")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify liking events with different IDs")
    public void likeEventTest(long eventId, int expectedStatus, String expectedMessage) {
        Response response = eventClient.likeEvent(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        if (expectedStatus != 200 && expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Validation message should match");
        }

        if (expectedStatus == 200) {
            Long currentUserId = signIn.getUserId();
            List<EventUserLikeEntity> likesFromDb = eventUserLikeService.getByEventId(eventId);
            boolean userLiked = likesFromDb.stream()
                    .anyMatch(like -> like.getEventId().equals(eventId) && like.getUsersId().equals(currentUserId));
            softAssert.assertTrue(userLiked, "DB: User should have liked event with id " + eventId);
        } else if (expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Validation message should match");
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "dislikeEventCases")
    public Object[][] provideDislikeEventCases() {
        return new Object[][]{
                {1L, 200, null},
                {5L, 404, "Event doesn't exist by this id: 5"},
                {4L, 400, "Current user has no permission for this action"}
        };
    }

    @Test(dataProvider = "dislikeEventCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify dislike event API with various scenarios: success, no permission, not found")
    public void dislikeEventTest(long eventId, int expectedStatus, String expectedMessage) {
        Response response = eventClient.dislikeEvent(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        if (expectedStatus != 200 && expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Validation message should match");
        }

        if (expectedStatus == 200) {
            List<EventUserDislikeEntity> dislikesFromDb = eventUserDislikeService.getByEventId(eventId);
            softAssert.assertFalse(dislikesFromDb.isEmpty(), "Dislikes should be present in DB for eventId: " + eventId);

            boolean userDisliked = dislikesFromDb.stream()
                    .anyMatch(dislike -> dislike.getUserId().equals(signIn.getUserId()));
            softAssert.assertTrue(userDisliked, "Current user should have disliked the event");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "likesCountCases")
    public Object[][] provideLikesCountCases() {
        return new Object[][]{
                {2L, 200, null},
                {99L, 404, "Event doesn't exist by this id: 99"}
        };
    }

    @Test(dataProvider = "likesCountCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify getting likes count for existing and non-existing events")
    public void getLikesCountTest(long eventId, int expectedStatus, String expectedMessage) {
        Response response = eventClient.getEventLikesCount(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        Integer apiCount = null;

        if (expectedStatus == 200) {
            apiCount = response.as(Integer.class);
            softAssert.assertTrue(apiCount >= 0, "Likes count should be >= 0");
        } else if (expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Error message should match");
        }

        if (expectedStatus == 200 && apiCount != null) {
            List<EventUserLikeEntity> likesFromDb = eventUserLikeService.getByEventId(eventId);
            int dbCount = likesFromDb.size();
            softAssert.assertEquals(apiCount.intValue(), dbCount, "API likes count should match DB count");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "likeStatusCases")
    public Object[][] provideLikeStatusCases() {
        return new Object[][]{
                {3L, 200, true, null},
                {1L, 200, false, null},
                {99L, 404, null, "Event doesn't exist by this id: 99"}
        };
    }

    @Test(dataProvider = "likeStatusCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify if user liked the event (true/false) or check for 404 if event does not exist")
    public void checkUserLikeStatus(long eventId, int expectedStatus, Boolean expectedLiked, String expectedErrorMessage) {
        Response response = eventClient.checkUserLikedEvent(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Status code should match");

        if (expectedStatus == 200) {
            Boolean actualLiked = response.as(Boolean.class);
            softAssert.assertEquals(actualLiked, expectedLiked, "Liked status should match");

            List<EventUserLikeEntity> likesFromDb = eventUserLikeService.getByEventId(eventId);
            Long currentUserId = signIn.getUserId();

            boolean isLikedInDb = likesFromDb.stream()
                    .anyMatch(like -> like.getUsersId().equals(currentUserId));

            softAssert.assertEquals(Boolean.valueOf(isLikedInDb), expectedLiked, "Liked status in DB should match expected");

        } else if (expectedStatus == 404 && expectedErrorMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedErrorMessage, "Error message should match");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "dislikesCountCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    public Object[][] provideDislikesCountCases() {
        return new Object[][]{
                {2L, 200, null},
                {99L, 404, "Event doesn't exist by this id: 99"}
        };
    }

    @Test(dataProvider = "dislikesCountCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify getting likes count for existing and non-existing events")
    public void getDislikesCountTest(long eventId, int expectedStatus, String expectedMessage) {
        Response response = eventClient.getEventDislikesCount(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        if (expectedStatus == 200) {
            Integer apiCount = response.as(Integer.class);
            softAssert.assertTrue(apiCount >= 0, "Dislikes count should be >= 0");

            List<EventUserDislikeEntity> dislikesFromDb = eventUserDislikeService.getByEventId(eventId);
            int dbCount = dislikesFromDb.size();
            softAssert.assertEquals(apiCount.intValue(), dbCount, "API dislikes count should match DB count");

        } else if (expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Error message should match");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "updateEventCases")
    public Object[][] updateEventCases() {
        return new Object[][]{
                {8L},
        };
    }

    @Test(dataProvider = "updateEventCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify updating an event with a JSON file by event ID and validate updated fields")
    public void updateEventWithJsonFile(long eventId) {
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

        EventEntity eventFromDb = eventService.getEventById(eventId);
        softAssert.assertNotNull(eventFromDb, "Event should exist in DB");

        softAssert.assertEquals(eventFromDb.getTitle(), eventAfter.getTitle(), "Title in DB should match updated title");
        softAssert.assertEquals(eventFromDb.getDescription(), eventAfter.getDescription(), "Description in DB should match updated description");
        softAssert.assertEquals(eventFromDb.getId(), eventAfter.getId(), "Event ID in DB should match");
        softAssert.assertEquals(eventFromDb.getOrganizerId(), eventAfter.getOrganizer().getId(), "Organizer ID in DB should match");
        softAssert.assertAll();
    }

    @DataProvider(name = "deleteEventCases")
    public Object[][] deleteEventCases() {
        return new Object[][]{
                {10L, 200, null},
                {3L, 403, "Current user has no permission for this action"},
                {99L, 404, "Event hasn't been found"}
        };
    }

    @Test(dataProvider = "deleteEventCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify deleting an event by ID and check that it is no longer retrievable or error responses")
    public void deleteEvent(long eventId, int expectedStatus, String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();

        Response deleteResponse = eventClient.deleteEvent(eventId);
        softAssert.assertEquals(deleteResponse.getStatusCode(), expectedStatus, "Delete status should be " + expectedStatus);

        if (expectedStatus == 200) {
            Response getResponse = eventClient.getEventById(eventId);
            softAssert.assertEquals(getResponse.getStatusCode(), 404, "Event should not be found after deletion");

            EventEntity eventFromDb = eventService.getEventById(eventId);
            softAssert.assertNull(eventFromDb, "Event should be null in DB after deletion");
        } else if (expectedMessage != null) {
            String actualMessage = deleteResponse.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Error message should match");
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "favoriteEventIds")
    public Object[][] favoriteEventIds() {
        return new Object[][]{
                {2L, 200, null},
                {3L, 400, "User has already added this event to favorites."},
                {99L, 404, "Event doesn't exist by this id: 99"}
        };
    }

    @Test(dataProvider = "favoriteEventIds")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify adding events to favorites with check if event already added")
    public void addEventToFavorites(long eventId, int expectedStatus, String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();

        Response eventResponse = eventClient.getEventById(eventId);

        if (eventResponse.getStatusCode() == 200) {
            EventsPage event = eventResponse.as(EventsPage.class);
            if (event.isFavorite()) {
                Response response = eventClient.addEventToFavorites(eventId);
                softAssert.assertEquals(response.getStatusCode(), 400, "Status code for already added event should be 400");
                String actualMessage = response.jsonPath().getString("message");
                softAssert.assertEquals(actualMessage, "User has already added this event to favorites.", "Error message should match");
                softAssert.assertAll();
                return;
            }
        } else if (expectedStatus == 404) {
            Response response = eventClient.addEventToFavorites(eventId);
            softAssert.assertEquals(response.getStatusCode(), 404, "Status code should be 404 for not found");
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Error message should match");
            softAssert.assertAll();
            return;
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "favoriteEventIdsForRemove")
    public Object[][] favoriteEventIdsForRemove() {
        return new Object[][]{
                {2L, 200, null},
                {3L, 400, "This event is not in favorites."},
                {99L, 404, "Event doesn't exist by this id: 99"}
        };
    }

    @Test(dataProvider = "favoriteEventIdsForRemove")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify removing events from favorites with check if event is in favorites before removal")
    public void removeEventFromFavorites(long eventId, int expectedStatus, String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();

        Response eventResponse = eventClient.getEventById(eventId);

        if (eventResponse.getStatusCode() == 200) {
            EventsPage event = eventResponse.as(EventsPage.class);

            if (!event.isFavorite()) {
                Response response = eventClient.removeEventFromFavorites(eventId);
                softAssert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 when event is not in favorites");
                String actualMessage = response.jsonPath().getString("message");
                softAssert.assertEquals(actualMessage, "This event is not in favorites.", "Error message should match");
                softAssert.assertAll();
                return;
            }
        } else {
            Response response = eventClient.removeEventFromFavorites(eventId);
            softAssert.assertEquals(response.getStatusCode(), 404, "Status code should be 404 for not found event");
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Error message should match");
            softAssert.assertAll();
            return;
        }

        Response response = eventClient.removeEventFromFavorites(eventId);
        softAssert.assertEquals(response.getStatusCode(), expectedStatus, "Status code should be 200 for successful removal");

        Response checkResponse = eventClient.getEventById(eventId);
        if (checkResponse.getStatusCode() == 200) {
            EventsPage eventAfter = checkResponse.as(EventsPage.class);
            softAssert.assertFalse(eventAfter.isFavorite(), "Event should no longer be marked as favorite after removal");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "rateEventCases")
    public Object[][] provideRateEventCases() {
        return new Object[][]{
                {2L, 1, 200, null},
                {2L, 2, 200, null},
                {2L, 3, 200, null},

                {2L, 4, 400, "must be less than or equal to 3"},
                {3L, 0, 400, "must be greater than or equal to 1"},
                {3L, -1, 400, "must be greater than or equal to 1"},
                {3L, 999, 400, "must be less than or equal to 3"},

                {8L, 3, 403, "Organizer have no rights to rate the own event"},
                {1L, 2, 400, "Event is not finished yet"},
                {99L, 2, 404, "Event hasn't been found"}
        };
    }

    @Test(dataProvider = "rateEventCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify rating event with various grades and validate status and messages")
    public void rateEventWithValidation(long eventId, int grade, int expectedStatus, String expectedMessage) {
        Response response = eventClient.rateEvent(eventId, grade);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        if (expectedStatus != 200 && expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Validation message should match");
        }

        if (expectedStatus == 200) {
            List<EventGradeEntity> gradesFromDb = eventGradeService.getByEventId(eventId);
            boolean found = gradesFromDb.stream()
                    .anyMatch(g -> g.getGrade() != null && g.getGrade() == grade);
            softAssert.assertTrue(found, "DB should contain the rating with grade = " + grade);
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "eventAttendersCases")
    public Object[][] provideEventAttendersCases() {
        return new Object[][]{
                {1L, 200, true},
                {99L, 404, false}
        };
    }

    @Test(dataProvider = "eventAttendersCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify getting event attenders with valid and invalid event IDs")
    public void getEventAttendersTest(long eventId, int expectedStatus, boolean expectList) {
        Response response = eventClient.getEventAttenders(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        if (expectedStatus == 200) {
            List<EventAttender> attenders = response.jsonPath().getList("", EventAttender.class);
            softAssert.assertNotNull(attenders, "Attenders list should not be null");
            if (expectList) {
                softAssert.assertTrue(attenders.size() >= 0, "Attenders list size should be >= 0");

                List<EventAttenderEntity> attendersFromDb = eventAttenderService.getByEventId(eventId);
                int dbCount = attendersFromDb.size();
                softAssert.assertEquals(attenders.size(), dbCount, "API attenders count should match DB count");
            }
        } else if (expectedStatus == 404) {
            String message = response.jsonPath().getString("message");
            softAssert.assertEquals(message, "Event hasn't been found", "Expected error message");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "attenderCases")
    public Object[][] provideAttenderCases() {
        return new Object[][]{
                {1L, 200, null},
                {1L, 400, "You have already subscribed on this event"},
                {99L, 404, "Event hasn't been found"}
        };
    }

    @Test(dataProvider = "attenderCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify adding attender to event: success, already subscribed, not found")
    public void addAttenderToEventTest(long eventId, int expectedStatus, String expectedMessage) {
        Response response = eventClient.addAttenderToEvent(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        if (expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Validation message should match");
        }

        if (expectedStatus == 200) {
            List<EventAttenderEntity> attendersFromDb = eventAttenderService.getByEventId(eventId);
            boolean userIsAttender = attendersFromDb.stream()
                    .anyMatch(attender -> attender.getUserId().equals(signIn.getUserId()));
            softAssert.assertTrue(userIsAttender, "User should be present in attenders list in DB after subscription");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "attendersCountData")
    public Object[][] attendersCountData() {
        return new Object[][]{
                {5L, 2},
                {99L, 0},
        };
    }

    @Test(dataProvider = "attendersCountData")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify count of events where user is an attender")
    public void getAttendersEventsCountTest(long userId, int expectedCount) {
        Response response = eventClient.getEventAttendersCount(userId);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        int actualCount = response.as(Integer.class);
        softAssert.assertEquals(actualCount, expectedCount, "Count should match expected value");

        List<EventAttenderEntity> attendersFromDb = eventAttenderService.getByUserId(userId);
        int dbCount = attendersFromDb.size();
        softAssert.assertEquals(dbCount, expectedCount, "Count from DB should match expected value");

        softAssert.assertAll();
    }

    @DataProvider(name = "addToRequestedCases")
    public Object[][] provideAddToRequestedCases() {
        return new Object[][]{
                {12L, 200, null},
                {12L, 400, "User has already added this event to requested."},
                {99L, 404, "Event doesn't exist by this id: 99"}
        };
    }

    @Test(dataProvider = "addToRequestedCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify adding an event to requested list and check if user appears in attenders list after success")
    public void addToRequestedEventTest(long eventId, int expectedStatus, String expectedMessage) {
        Response response = eventClient.addEventToRequested(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        if (expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Validation message should match");
        }

        if (expectedStatus == 200) {
            Response attendersResponse = eventClient.getEventAttenders(eventId);
            softAssert.assertEquals(attendersResponse.statusCode(), 200, "Attenders status code should be 200");

            var attenders = attendersResponse.jsonPath().getList("id", Integer.class);
            Long currentUserId = signIn.getUserId();
            softAssert.assertTrue(attenders.contains(currentUserId.intValue()), "Current user should be in attenders list");

            List<EventRequesterEntity> requestedList = eventRequesterService.getByEventId(eventId);

            boolean foundInRequested = requestedList.stream()
                    .anyMatch(r -> currentUserId.equals(r.getUserId()));

            softAssert.assertTrue(foundInRequested, "User should be present in event_requesters table for this event");
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "requestedUsersCases")
    public Object[][] provideRequestedUsersCases() {
        return new Object[][]{
                {8L, 200, null},
                {99L, 404, "Event hasn't been found"},
                {1L, 403, "Current user has no permission for this action"}
        };
    }

    @Test(dataProvider = "requestedUsersCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify getting requested users list for an event and validate access, presence and data")
    public void getRequestedUsersTest(long eventId, int expectedStatus, String expectedMessage) {
        Response response = eventClient.getRequestedUsers(eventId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Unexpected status code");

        if (expectedStatus == 200) {
            int totalElements = response.jsonPath().getInt("totalElements");
            softAssert.assertTrue(totalElements >= 0, "totalElements should be >= 0");

            List<Map<String, Object>> page = response.jsonPath().getList("page");
            softAssert.assertNotNull(page, "User list 'page' should not be null");

            if (!page.isEmpty()) {
                Map<String, Object> user = page.get(0);
                softAssert.assertNotNull(user.get("id"), "User ID should not be null");
                softAssert.assertNotNull(user.get("name"), "User name should not be null");
                softAssert.assertNotNull(user.get("email"), "User email should not be null");
            }

            List<EventRequesterEntity> requestedUsers = eventRequesterService.getByEventId(eventId);
            softAssert.assertEquals(requestedUsers.size(), totalElements, "Number of requested users should match DB count");

            List<Long> apiUserIds = page.stream()
                    .map(userMap -> ((Number) userMap.get("id")).longValue())
                    .collect(Collectors.toList());

            for (EventRequesterEntity requester : requestedUsers) {
                softAssert.assertTrue(apiUserIds.contains(requester.getUserId()),
                        "User ID " + requester.getUserId() + " from DB should be present in API response");
            }

        } else if (expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Error message should match");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "removeRequestedCases")
    public Object[][] provideRemoveRequestedCases() {
        return new Object[][]{
                {12L, 200, null},
                {3L, 400, "This event is not in requested."},
                {99L, 404, "Event doesn't exist by this id: 99"}
        };
    }

    @Test(dataProvider = "removeRequestedCases")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Test removing events from requested list without attempting to add them first")
    public void removeEventFromRequestedTest(long eventId, int expectedStatus, String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();

        Response response = eventClient.removeEventFromRequested(eventId);
        int actualStatus = response.getStatusCode();
        softAssert.assertEquals(actualStatus, expectedStatus, "Status code should match");

        if (expectedStatus != 200 && expectedMessage != null) {
            String actualMessage = response.jsonPath().getString("message");
            softAssert.assertEquals(actualMessage, expectedMessage, "Error message should match");
        }

        if (expectedStatus == 200) {
            Long currentUserId = signIn.getUserId();
            List<EventRequesterEntity> requestedList = eventRequesterService.getByEventId(eventId);

            boolean userStillRequested = requestedList.stream()
                    .anyMatch(r -> currentUserId.equals(r.getUserId()));

            softAssert.assertFalse(userStillRequested, "User should NOT be present in event_requesters table for this event after removal");
        }

        softAssert.assertAll();
    }

    @DataProvider(name = "organizerCounts")
    public Object[][] provideOrganizerCounts() {
        return new Object[][]{
                {5L, 200, 3},
                {99L, 200, 0}
        };
    }

    @Test(dataProvider = "organizerCounts")
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify count of events where user is organizer")
    public void getEventsCountByOrganizerTest(long userId, int expectedStatus, int expectedCount) {
        Response response = eventClient.getEventsCountByOrganizer(userId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedStatus, "Status code should be " + expectedStatus);

        if (expectedStatus == 200) {
            int actualCount = response.as(Integer.class);
            softAssert.assertEquals(actualCount, expectedCount, "Events count should match expected");
        }
        softAssert.assertAll();
    }

    @Test
    @Issue("166")
    @Owner("Svitlana Kovalova")
    @Description("Verify getting all events addresses returns 200 and non-empty list")
    public void getAllEventsAddressesTest() {
        Response response = eventClient.getEventsAddresses();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        List<Map<String, Object>> apiAddresses = response.jsonPath().getList("$");
        softAssert.assertNotNull(apiAddresses, "API addresses list should not be null");
        softAssert.assertTrue(apiAddresses.size() > 0, "API addresses list should not be empty");

        if (!apiAddresses.isEmpty()) {
            var firstAddress = response.jsonPath().getMap("[0]");
            softAssert.assertTrue(firstAddress.containsKey("latitude"), "Address should have latitude");
            softAssert.assertTrue(firstAddress.containsKey("longitude"), "Address should have longitude");
            softAssert.assertTrue(firstAddress.containsKey("streetEn"), "Address should have streetEn");
            softAssert.assertTrue(firstAddress.containsKey("cityUa"), "Address should have cityUa");
            softAssert.assertTrue(firstAddress.containsKey("countryUa"), "Address should have countryUa");

            List<EventDateLocationEntity> dbAddresses = eventDateLocationService.getAll();
            Map<Long, List<EventDateLocationEntity>> dbGroupedByEventId = dbAddresses.stream()
                    .filter(e -> e.getEventId() != null)
                    .collect(Collectors.groupingBy(EventDateLocationEntity::getEventId));

            Map<Long, List<Map<String, Object>>> apiGroupedByEventId = apiAddresses.stream()
                    .filter(addr -> addr.get("eventId") != null)
                    .collect(Collectors.groupingBy(addr -> ((Number) addr.get("eventId")).longValue()));

            for (Map.Entry<Long, List<Map<String, Object>>> entry : apiGroupedByEventId.entrySet()) {
                Long eventId = entry.getKey();
                List<Map<String, Object>> apiList = entry.getValue();
                List<EventDateLocationEntity> dbList = dbGroupedByEventId.get(eventId);

                softAssert.assertNotNull(dbList, "DB should have addresses for eventId: " + eventId);
                if (dbList == null) continue;

                softAssert.assertEquals(apiList.size(), dbList.size(),
                        "Number of addresses should match for eventId: " + eventId);

                for (int i = 0; i < Math.min(apiList.size(), dbList.size()); i++) {
                    Map<String, Object> apiAddr = apiList.get(i);
                    EventDateLocationEntity dbAddr = dbList.get(i);

                    softAssert.assertEquals(
                            Double.parseDouble(apiAddr.get("latitude").toString()),
                            dbAddr.getLatitude(),
                            "Latitude mismatch for eventId: " + eventId + " at index " + i
                    );
                    softAssert.assertEquals(
                            Double.parseDouble(apiAddr.get("longitude").toString()),
                            dbAddr.getLongitude(),
                            "Longitude mismatch for eventId: " + eventId + " at index " + i
                    );
                    softAssert.assertEquals(
                            apiAddr.get("streetEn"),
                            dbAddr.getStreetEn(),
                            "StreetEn mismatch for eventId: " + eventId + " at index " + i
                    );
                    softAssert.assertEquals(
                            apiAddr.get("cityUa"),
                            dbAddr.getCityUa(),
                            "CityUa mismatch for eventId: " + eventId + " at index " + i
                    );
                    softAssert.assertEquals(
                            apiAddr.get("countryUa"),
                            dbAddr.getCountryUa(),
                            "CountryUa mismatch for eventId: " + eventId + " at index " + i
                    );
                }
            }
            softAssert.assertAll();
        }
    }
}
