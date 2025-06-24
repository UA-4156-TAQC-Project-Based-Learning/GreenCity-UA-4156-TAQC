package com.greencity.api.models.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EventsPage {
    private Long id;
    private String title;
    private EventOrganizer organizer;
    private String creationDate;
    private String description;
    private List<EventDate> dates;
    private List<EventTag> tags;
    private String titleImage;
    private List<String> additionalImages;
    private String type;
    private boolean isRelevant;
    private int likes;
    private int dislikes;
    private int countComments;
    private int eventRate;
    private Integer currentUserGrade;
    private boolean open;
    private boolean isSubscribed;
    private boolean isFavorite;
    private boolean isOrganizedByFriend;
}
