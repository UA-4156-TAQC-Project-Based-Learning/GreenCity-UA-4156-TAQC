package com.greencity.api.models.habits;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseHabits {
    public ArrayList<HabitPage> habitPage;
    public int totalElements;
    public int currentPage;
    public int totalPages;
}
