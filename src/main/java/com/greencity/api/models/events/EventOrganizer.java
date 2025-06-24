package com.greencity.api.models.events;

import lombok.Data;

@Data
public class EventOrganizer {
    private Long id;
    private String name;
    private Integer organizerRating;
    private String email;
}
