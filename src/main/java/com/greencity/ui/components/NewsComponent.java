package com.greencity.ui.components;

import com.greencity.ui.components.baseComponents.BaseComponent;
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

//    private final WebElement title;
//    private final WebElement image;
//    @Getter
//    private final List<WebElement> tags;
//    private final WebElement publicationDate;
//
//    public NewsComponent(WebDriver driver, WebElement rootElement) {
//        super(driver, rootElement);
//        this.title = rootElement.findElement(By.xpath(".//div[@class='title-list word-wrap']"));
//        this.image = rootElement.findElement(By.xpath(".//img[@class='list-image-content']"));
//        this.tags = rootElement.findElements(By.xpath(".//div[@class='filter-tag']"));
//        this.publicationDate = rootElement.findElement(By.xpath(".//p[contains(@class, 'user-data-text-date')]/span"));
//    }

    public String getTitleText() {
        return title.getText().trim();
    }

    public LocalDate getPublicationDate() {
        String dateText = publicationDate.getText().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
        return LocalDate.parse(dateText, formatter);
    }

    public void click() {
        title.click();
    }
}




