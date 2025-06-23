package com.greencity.api.models.habits;

import lombok.Data;

@Data
public class AssignedHabitResponse {

    private int id;
    private String status;
    private int userId;
    private HabitPage habit;

}
