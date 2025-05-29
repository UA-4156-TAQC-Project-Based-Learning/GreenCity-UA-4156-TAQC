package com.greencity.ui.pages.abstractNewsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreviewNewsPage extends AbstractNewsPage {

    @FindBy(css = ".create-news-text")
    private WebElement createNewsTitle;

    @FindBy(xpath = "//div[@class='button-text' and text()='Back to editing']")
    private WebElement backToEditingButton;

    public PreviewNewsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCreateNewsTitle() {
        return createNewsTitle.getText();
    }

    public void clickBackToEditing() {
        backToEditingButton.click();
    }
}

