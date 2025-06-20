package com.greencity.api.models.habits;

import lombok.Data;

@Data
public class CustomToDoListItem {
    public int id;
    public String text;
    public String status;
}
