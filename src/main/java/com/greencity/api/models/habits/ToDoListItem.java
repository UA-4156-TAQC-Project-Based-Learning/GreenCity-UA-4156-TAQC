package com.greencity.api.models.habits;

import lombok.Data;

@Data
public class ToDoListItem {
    public int id;
    public String text;
    public String status;
}
