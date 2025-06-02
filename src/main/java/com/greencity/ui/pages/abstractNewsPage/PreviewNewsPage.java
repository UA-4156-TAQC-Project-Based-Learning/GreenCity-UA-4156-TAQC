package com.greencity.ui.pages.abstractNewsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreviewNewsPage extends AbstractNewsPage {

    @FindBy(xpath = "//*[contains(@class,'create-news-text')]")
    private WebElement createNewsTitle;

    @FindBy(xpath = "//div[@class='button-text' and text()='Back to editing']")
    private WebElement backToEditingButton;

    public PreviewNewsPage(WebDriver driver) {
        super(driver);
    }

    public String getCreateNewsTitle() {
        return createNewsTitle.getText().trim();
    }

    public void clickBackToEditing() {
        backToEditingButton.click();
    }
}

