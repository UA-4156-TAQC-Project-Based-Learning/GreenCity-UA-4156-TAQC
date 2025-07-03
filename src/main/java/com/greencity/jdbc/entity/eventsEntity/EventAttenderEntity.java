package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.util.List;

@Data
public class EventAttenderEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_attenders";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_attenders WHERE event_id = %d";
    public static final String FIND_BY_USER_ID = "SELECT * FROM events_attenders WHERE user_id = %d";

    private Long eventId;
    private Long userId;

    public static EventAttenderEntity parseRow(List<String> row) {
        EventAttenderEntity eventAttenderEntity = new EventAttenderEntity();
        eventAttenderEntity.setEventId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventAttenderEntity.setUserId(row.get(1) == null ? null : Long.parseLong(row.get(1)));
        return eventAttenderEntity;
    }
}
