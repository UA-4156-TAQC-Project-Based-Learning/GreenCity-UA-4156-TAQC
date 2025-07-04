package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.util.List;

@Data
public class EventUserDislikeEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_users_dislikes";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_users_dislikes WHERE event_id = %d";
    public static final String FIND_BY_USER_ID = "SELECT * FROM events_users_dislikes WHERE user_id = %d";

    private Long eventId;
    private Long userId;

    public static EventUserDislikeEntity parseRow(List<String> row) {
        EventUserDislikeEntity eventUserDislikeEntity = new EventUserDislikeEntity();
        eventUserDislikeEntity.setEventId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventUserDislikeEntity.setUserId(row.get(1) == null ? null : Long.parseLong(row.get(1)));
        return eventUserDislikeEntity;
    }
}
