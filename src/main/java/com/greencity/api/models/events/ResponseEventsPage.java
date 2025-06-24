package com.greencity.api.models.events;

import lombok.Data;
import java.util.List;

@Data
public class ResponseEventsPage {
    private List<EventsPage> page;
    private int totalElements;
    private int currentPage;
    private int totalPages;
    private int number;
    private boolean hasPrevious;
    private boolean hasNext;
    private boolean first;
    private boolean last;
}
