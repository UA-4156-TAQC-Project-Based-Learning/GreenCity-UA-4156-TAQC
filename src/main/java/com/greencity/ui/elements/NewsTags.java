package com.greencity.ui.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
@AllArgsConstructor
public enum NewsTags {

    NEWS_TAG(By.cssSelector("button.tag-button"), "News", "Новини"),
    EVENTS_TAG(By.cssSelector("button.tag-button:nth-of-type(2)"), "Events", "Події"),
    EDUCATION_TAG(By.cssSelector("button.tag-button:nth-of-type(3)"), "Education", "Освіта"),
    INITIATIVES_TAG(By.cssSelector("button.tag-button:nth-of-type(4)"), "Initiatives", "Ініціативи"),
    ADS_TAG(By.cssSelector("button.tag-button:nth-of-type(5)"), "Ads", "Реклама");

    private final By locator;
    private final String englishName;
    private final String ukrainianName;

}
