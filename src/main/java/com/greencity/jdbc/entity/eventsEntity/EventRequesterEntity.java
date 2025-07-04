package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.util.List;

@Data
public class EventRequesterEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_requesters";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_requesters WHERE event_id = %d";
    public static final String FIND_BY_USER_ID = "SELECT * FROM events_requesters WHERE user_id = %d";

    private Long eventId;
    private Long userId;

    public static EventRequesterEntity parseRow(List<String> row) {
        EventRequesterEntity eventRequesterEntity = new EventRequesterEntity();
        eventRequesterEntity.setEventId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventRequesterEntity.setUserId(row.get(1) == null ? null : Long.parseLong(row.get(1)));
        return eventRequesterEntity;
    }
}
