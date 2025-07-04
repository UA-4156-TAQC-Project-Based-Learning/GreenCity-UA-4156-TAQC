package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.util.List;

@Data
public class EventGradeEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_grades";
    public static final String FIND_BY_ID = "SELECT * FROM events_grades WHERE id = %d";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_grades WHERE event_id = %d";
    public static final String FIND_BY_USER_ID = "SELECT * FROM events_grades WHERE user_id = %d";

    private Long id;
    private Long eventId;
    private Long userId;
    private Integer grade;

    public static EventGradeEntity parseRow(List<String> row) {
        EventGradeEntity eventGradeEntity = new EventGradeEntity();
        eventGradeEntity.setId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventGradeEntity.setEventId(row.get(1) == null ? null : Long.parseLong(row.get(1)));
        eventGradeEntity.setUserId(row.get(2) == null ? null : Long.parseLong(row.get(2)));
        eventGradeEntity.setGrade(row.get(3) == null ? null : Integer.parseInt(row.get(3)));
        return eventGradeEntity;
    }
}