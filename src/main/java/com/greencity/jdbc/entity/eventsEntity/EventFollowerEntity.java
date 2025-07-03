package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.util.List;

@Data
public class EventFollowerEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_followers";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_followers WHERE event_id = %d";
    public static final String FIND_BY_USER_ID = "SELECT * FROM events_followers WHERE user_id = %d";

    private Long eventId;
    private Long userId;

    public static EventFollowerEntity parseRow(List<String> row) {
        EventFollowerEntity eventFollowerEntity = new EventFollowerEntity();
        eventFollowerEntity.setEventId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventFollowerEntity.setUserId(row.get(1) == null ? null : Long.parseLong(row.get(1)));
        return eventFollowerEntity;
    }
}
