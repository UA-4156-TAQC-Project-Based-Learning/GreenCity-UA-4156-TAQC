package com.greencity.api.models.habits;

import lombok.Data;

@Data
public class AssignedHabitPage {

    private int id;
    private String status;
    private int userId;
    private HabitPage habit;
    private int duration;
    private int workingDays;

}
