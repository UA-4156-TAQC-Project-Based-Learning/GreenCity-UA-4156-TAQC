package com.greencity.api.models.habits;

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
    private boolean isCustomHabit;
    private int usersIdWhoCreatedCustomHabit;
    private String habitAssignStatus;
    private boolean isAssigned;
    private boolean isFavorite;
    private int likes;
    private int dislikes;
}
