package com.greencity.ui.pages.econewspage;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.components.header.HeaderLoggedUserComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateEditNewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
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

    @Getter
    private List<NewsComponent> news;

    private By createButtonBy = By.xpath(".//span[contains(@id, 'create-button-text')]");

    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCreateButton() {
        return driver.findElement(createButtonBy);
    }
    public CreateEditNewsPage clickCreateButton() {
        getCreateButton().click();
        return new CreateEditNewsPage(driver);
    }
}
