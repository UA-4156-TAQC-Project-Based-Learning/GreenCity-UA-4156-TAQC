package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.util.List;

@Data
public class EventUserLikeEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_users_likes";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_users_likes WHERE event_id = %d";
    public static final String FIND_BY_USER_ID = "SELECT * FROM events_users_likes WHERE users_id = %d";

    private Long eventId;
    private Long usersId;

    public static EventUserLikeEntity parseRow(List<String> row) {
        EventUserLikeEntity eventUserLikeEntity = new EventUserLikeEntity();
        eventUserLikeEntity.setEventId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventUserLikeEntity.setUsersId(row.get(1) == null ? null : Long.parseLong(row.get(1)));
        return eventUserLikeEntity;
    }
}
