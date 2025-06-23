package com.greencity.api.models.habits;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class HabitPage {
    private int defaultDuration;
    private int amountAcquiredUsers;
    private HabitTranslation habitTranslation;
    private int id;
    private String image;
    private int complexity;
    private ArrayList<String> tags;
    private ArrayList<ToDoListItem> toDoListItems;
    private ArrayList<CustomToDoListItem> customToDoListItems;

    @JsonProperty("isCustomHabit")
    private boolean customHabit;
    private int usersIdWhoCreatedCustomHabit;
    private String habitAssignStatus;

    @JsonProperty("isAssigned")
    private boolean assigned;

    @JsonProperty("isFavorite")
    private boolean favorite;
    private int likes;
    private int dislikes;
}
