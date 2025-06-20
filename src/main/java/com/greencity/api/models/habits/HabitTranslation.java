package com.greencity.api.models.habits;

import lombok.Data;

@Data
public class HabitTranslation {
    public String name;
    public String description;
    public String habitItem;
    public String languageCode;
}
