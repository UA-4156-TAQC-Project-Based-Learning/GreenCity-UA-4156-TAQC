package com.greencity.api.clients;

import io.qameta.allure.Step;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class EventClient extends BaseClient {
    protected String resourceUrl = "/events";

    public EventClient(String baseUrl) {
        super(baseUrl);
    }

    @Step("Get all events (userId={userId}, page={page}, size={size})")
    public Response getAllEvents(long userId, int page, int size) {

        return preparedRequest()
                .queryParam("user-id", userId)
                .queryParam("page", page)
                .queryParam("size", size)
                .get(resourceUrl);
    }

    @Step("Get event by ID: {eventId}")
    public Response getEventById(long eventId) {
        return preparedRequest()
                .get(resourceUrl + "/" + eventId);
    }

    @Step("Create event with JSON body and {images.length} image(s)")
    public Response createEvent(File jsonFile, File... images) {
        String jsonBody;
        try {
            jsonBody = new String(Files.readAllBytes(jsonFile.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }

        MultiPartSpecification jsonPart = new MultiPartSpecBuilder(jsonBody)
                .controlName("addEventDtoRequest")
                .mimeType("application/json")
                .build();

        RequestSpecification request = preparedMultipartRequest()
                .multiPart(jsonPart);

        for (File image : images) {
            request.multiPart("images", image);
        }

        return request.post(resourceUrl);
    }

    @Step("Like event ID: {eventId}")
    public Response likeEvent(long eventId) {
        return preparedRequest()
                .post(resourceUrl + "/" + eventId + "/like");
    }

    @Step("Dislike event ID: {eventId}")
    public Response dislikeEvent(long eventId) {
        return preparedRequest()
                .post(resourceUrl + "/" + eventId + "/dislike");
    }

    @Step("Get likes count for event ID: {eventId}")
    public Response getEventLikesCount(long eventId) {
        return preparedRequest()
                .get(resourceUrl + "/" + eventId + "/likes/count");
    }

    @Step("Check if user liked event ID: {eventId}")
    public Response checkUserLikedEvent(long eventId) {
        return preparedRequest()
                .get(resourceUrl + "/" + eventId + "/likes");
    }

    @Step("Get dislikes count for event ID: {eventId}")
    public Response getEventDislikesCount(long eventId) {
        return preparedRequest()
                .get(resourceUrl + "/" + eventId + "/dislikes/count");
    }

    @Step("Update event ID: {eventId} with new JSON and {images.length} image(s)")
    public Response updateEvent(long eventId, File jsonFile, File... images) {
        String jsonBody;
        try {
            jsonBody = Files.readString(jsonFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }

        MultiPartSpecification jsonPart = new MultiPartSpecBuilder(jsonBody)
                .controlName("eventDto")
                .mimeType("application/json")
                .build();

        RequestSpecification request = preparedMultipartRequest()
                .multiPart(jsonPart);

        for (File image : images) {
            request.multiPart("images", image);
        }

        return request.put(resourceUrl + "/" + eventId);
    }

    @Step("Delete event ID: {eventId}")
    public Response deleteEvent(long eventId) {
        return preparedRequest()
                .delete(resourceUrl + "/" + eventId);
    }

    @Step("Add event ID: {eventId} to favorites")
    public Response addEventToFavorites(long eventId) {
        return preparedRequest()
                .post(resourceUrl + "/" + eventId + "/favorites");
    }

    @Step("Remove event ID: {eventId} from favorites")
    public Response removeEventFromFavorites(long eventId) {
        return preparedRequest()
                .delete(resourceUrl + "/" + eventId + "/favorites");
    }

    @Step("Rate event ID: {eventId} with grade: {grade}")
    public Response rateEvent(long eventId, int grade) {
        return preparedRequest()
                .body(grade)
                .post(resourceUrl + "/" + eventId + "/ratings");
    }

    @Step("Get attenders for event ID: {eventId}")
    public Response getEventAttenders(long eventId) {
        return preparedRequest()
                .get(resourceUrl + "/" + eventId + "/attenders");
    }

    @Step("Add attender to event ID: {eventId}")
    public Response addAttenderToEvent(long eventId) {
        return preparedRequest()
                .post(resourceUrl + "/" + eventId + "/attenders");
    }

    @Step("Get number of events where user ID: {userId} is an attender")
    public Response getEventAttendersCount(long userId) {
        return preparedRequest()
                .queryParam("user-id", userId)
                .get(resourceUrl + "/attenders" + "/count");
    }

    @Step("Add event ID: {eventId} to requested")
    public Response addEventToRequested(long eventId) {
        return preparedRequest()
                .post(resourceUrl + "/" + eventId + "/addToRequested");
    }

    @Step("Get requested users for event ID: {eventId}")
    public Response getRequestedUsers(long eventId) {
        return preparedRequest()
                .get(resourceUrl + "/" + eventId + "/requested-users");
    }

    @Step("Remove event ID: {eventId} from requested")
    public Response removeEventFromRequested(long eventId) {
        return preparedRequest()
                .delete(resourceUrl + "/" + eventId + "/removeFromRequested");
    }

    @Step("Get events count by organizer with user ID: {userId}")
    public Response getEventsCountByOrganizer(long userId) {
        return preparedRequest()
                .queryParam("user-id", userId)
                .get(resourceUrl + "/organizers" + "/count");
    }

    @Step("Get all event addresses")
    public Response getEventsAddresses() {
        return preparedRequest()
                .get(resourceUrl + "/addresses");
    }

}