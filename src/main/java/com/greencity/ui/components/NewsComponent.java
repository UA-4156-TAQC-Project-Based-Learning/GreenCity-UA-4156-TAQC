package com.greencity.ui.components;

import com.greencity.ui.components.baseComponents.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NewsComponent extends BaseComponent {

//    public NewsComponent(WebDriver driver, WebElement rootElement) {
//        super(driver, rootElement);
//    }

//    @FindBy(xpath = "(.//div[@class='title-list word-wrap'])[1]")
//    private WebElement title;
//
//    @FindBy(xpath = "(.//div[@class='list-image-content'])[1]")
//    private WebElement image;
//
//    @FindBy(xpath = ".//div[@class='filter-tag']")
//    private List<WebElement> tags;


    private final WebElement title;

    private final WebElement image;

    private final List<WebElement> tags;

    public NewsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.title = rootElement.findElement(By.xpath(".//div[@class='title-list word-wrap']"));
        this.image = rootElement.findElement(By.xpath(".//img[@class='list-image-content']"));
        this.tags = rootElement.findElements(By.xpath(".//div[@class='filter-tag']"));
    }

    public List<String> getTagTexts() {
        return tags.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }


}
