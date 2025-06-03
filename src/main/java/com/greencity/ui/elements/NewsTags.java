package com.greencity.ui.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
@AllArgsConstructor
public enum NewsTags {

    NEWS_TAG(By.cssSelector("button.tag-button")),
    EVENTS_TAG(By.cssSelector("button.tag-button:nth-of-type(2)")),
    EDUCATION_TAG(By.cssSelector("button.tag-button:nth-of-type(3)")),
    INITIATIVES_TAG(By.cssSelector("button.tag-button:nth-of-type(4)")),
    ADS_TAG(By.cssSelector("button.tag-button:nth-of-type(5)"));

    private final By locator;

}
