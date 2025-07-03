package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.util.List;

@Data
public class EventTagEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_tags";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_tags WHERE event_id = %d";
    public static final String FIND_BY_TAG_ID = "SELECT * FROM events_tags WHERE tag_id = %d";

    private Long eventId;
    private Long tagId;

    public static EventTagEntity parseRow(List<String> row) {
        EventTagEntity eventTagEntity = new EventTagEntity();
        eventTagEntity.setEventId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventTagEntity.setTagId(row.get(1) == null ? null : Long.parseLong(row.get(1)));
        return eventTagEntity;
    }
}
