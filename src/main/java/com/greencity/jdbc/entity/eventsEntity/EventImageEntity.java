package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.util.List;

@Data
public class EventImageEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_images";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_images WHERE event_id = %d";

    private Long id;
    private String link;
    private Long eventId;

    public static EventImageEntity parseRow(List<String> row) {
        EventImageEntity eventImageEntity = new EventImageEntity();
        eventImageEntity.setId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventImageEntity.setLink(row.get(1));
        eventImageEntity.setEventId(row.get(2) == null ? null : Long.parseLong(row.get(2)));
        return eventImageEntity;
    }
}
