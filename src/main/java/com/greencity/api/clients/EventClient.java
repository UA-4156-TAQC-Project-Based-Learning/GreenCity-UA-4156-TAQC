package com.greencity.api.clients;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class EventClient extends BaseClient {
    protected String recourseUrl = "/events";

    public EventClient(String baseUrl) {
        super(baseUrl);
    }

    public Response getAllEvents(long userId, int page, int size) {

        return preparedRequest()
                .queryParam("user-id", userId)
                .queryParam("page", page)
                .queryParam("size", size)
                .get(recourseUrl);
    }

    public Response getEventById(long eventId) {
        return preparedRequest()
                .get(recourseUrl + "/" + eventId);
    }

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

        return request.post(recourseUrl);
    }

    public Response likeEvent(long eventId) {
        return preparedRequest()
                .post("/events/" + eventId + "/like");
    }

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

        return request.put("/events/" + eventId);
    }

    public Response deleteEvent(long eventId) {
        return preparedRequest()
                .delete(recourseUrl + "/" + eventId);
    }

    public Response addEventToFavorites(long eventId) {
        return preparedRequest()
                .post(recourseUrl + "/" + eventId + "/favorites");
    }

    public Response removeEventFromFavorites(long eventId) {
        return preparedRequest()
                .delete(recourseUrl + "/" + eventId + "/favorites");
    }
}