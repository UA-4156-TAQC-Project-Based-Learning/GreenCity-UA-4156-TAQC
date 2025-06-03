package com.greencity.ui.pages.econewspage;

import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@Getter
public class EcoNewsPage extends BasePage {

    @FindBy(xpath = ".//h1[contains(@class, 'main-header')]")
    private WebElement title;

    @FindBy(xpath = ".//button[contains(@class, 'tag-button')]")
    private List<WebElement> tags;

    @FindBy(xpath = "//div[@id='create-button']")
    private WebElement createNewsButton;


    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateNewsButton() {
        createNewsButton.click();
    }



}
