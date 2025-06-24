package com.greencity.api.models.events;

import lombok.Data;

@Data
public class EventDate {
    private Long id;
    private String startDate;
    private String finishDate;
    private String onlineLink;
    private EventCoordinates coordinates;
    private EventsPage event;
}
