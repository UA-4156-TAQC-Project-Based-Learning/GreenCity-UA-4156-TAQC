package com.greencity.api.models.habits;

import lombok.Data;

import java.util.List;

@Data
public class ResponseAssignedHabits {
    private List<AssignedHabitPage> page;
    private int totalElements;
    private int currentPage;
    private int totalPages;
    private boolean hasPrevious;
    private boolean hasNext;
    private boolean first;
    private boolean last;
    private int number;
}
