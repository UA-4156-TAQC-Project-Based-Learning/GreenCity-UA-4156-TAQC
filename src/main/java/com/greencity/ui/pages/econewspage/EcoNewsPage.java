package com.greencity.ui.pages.econewspage;

import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateEditNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EcoNewsPage extends BasePage {

    @Getter
    @FindBy(xpath = ".//h1[contains(@class, 'main-header')]")
    private WebElement title;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'tag-button')]")
    private List<WebElement> tags;

//    @Getter
//    private List<NewsComponent> news;

    @FindBy(xpath = "//span[@id='create-button-text']")
    private WebElement createNewsButton;

    public CreateEditNewsPage clickCreateNewsButton() {
        createNewsButton.click();
        return new CreateEditNewsPage(driver);
    }

    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }


}
