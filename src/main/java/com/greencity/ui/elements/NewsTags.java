package com.greencity.ui.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
@AllArgsConstructor
public enum NewsTags {

    NEWS_TAG(By.cssSelector("button.tag-button"), "NEWS", "Новини"),
    EVENTS_TAG(By.cssSelector("button.tag-button:nth-of-type(2)"), "EVENTS", "Події"),
    EDUCATION_TAG(By.cssSelector("button.tag-button:nth-of-type(3)"), "EDUCATION", "Освіта"),
    INITIATIVES_TAG(By.cssSelector("button.tag-button:nth-of-type(4)"), "INITIATIVES", "Ініціативи"),
    ADS_TAG(By.cssSelector("button.tag-button:nth-of-type(5)"), "ADS", "Реклама");

    private final By locator;
    private final String englishName;
    private final String ukrainianName;

    public static boolean containsUkrainianName(String name) {
        for (NewsTags tag : NewsTags.values()) {
            if (tag.getUkrainianName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsEnglishName(String name) {
        for (NewsTags tag : NewsTags.values()) {
            if (tag.getEnglishName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static NewsTags getByName(String name) {
        for (NewsTags tag : NewsTags.values()) {
            if (tag.getEnglishName().equalsIgnoreCase(name) || tag.getUkrainianName().equalsIgnoreCase(name)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("No NewsTags with name: " + name);
    }

}
