package com.greencity.api.models.events;

import lombok.Data;
import java.util.List;

@Data
public class EventFilter {
    private String time;
    private List<String> cities;
    private List<String> statuses;
    private List<String> tags;
    private String title;
    private String type;
    private String from;
    private String to;
}
