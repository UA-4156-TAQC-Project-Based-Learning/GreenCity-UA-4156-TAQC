package com.greencity.ui.components;

import com.greencity.ui.components.baseComponents.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Getter
public class NewsComponent extends BaseComponent {

    @FindBy(xpath = "(.//div[@class='title-list word-wrap'])[1]")
    private WebElement title;

    @FindBy(xpath = "(.//div[@class='list-image-content'])[1]")
    private WebElement image;

    @FindBy(xpath = ".//div[@class='filter-tag']")
    private List<WebElement> tags;

    @FindBy(xpath = ".//p[contains(@class, 'user-data-text-date')]/span")
    private WebElement publicationDate;

    public NewsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Retrieve news title text")
    public String getTitleText() {
        return title.getText().trim();
    }

    @Step("Retrieve publication date of the news")
    public LocalDate getPublicationDate() {
        try {
            String dateText = publicationDate.getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            return LocalDate.parse(dateText, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse publication date: " + publicationDate.getText(), e);
        }
    }

    @Step("Click on news with title '{getTitleText()}'")
    public void click() {
        title.click();
    }
}




