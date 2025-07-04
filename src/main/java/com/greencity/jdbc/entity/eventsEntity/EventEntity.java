package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class EventEntity {
    public static final String SELECT_ALL = "SELECT * FROM events";
    public static final String FIND_BY_ID = "SELECT * FROM events WHERE id = %d";

    private Long id;
    private String title;
    private String description;
    private Long organizerId;
    private String titleImage;
    private Boolean open;
    private Date creationDate;
    private Long eventsCommentsId;
    private String type;

    public static EventEntity parseRow(List<String> row) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventEntity.setTitle(row.get(1));
        eventEntity.setDescription(row.get(2));
        eventEntity.setOrganizerId(row.get(3) == null ? null : Long.parseLong(row.get(3)));
        eventEntity.setTitleImage(row.get(4));
        eventEntity.setOpen(row.get(5) == null ? null : Boolean.parseBoolean(row.get(5)));
        eventEntity.setCreationDate(row.get(6) == null ? null : Date.valueOf(row.get(6)));
        eventEntity.setEventsCommentsId(row.get(7) == null ? null : Long.parseLong(row.get(7)));
        eventEntity.setType(row.get(8));
        return eventEntity;
    }
}
