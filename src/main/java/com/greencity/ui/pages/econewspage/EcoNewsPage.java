package com.greencity.ui.pages.econewspage;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.components.header.HeaderLoggedUserComponent;
import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.CreateEditNewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@Getter
public class EcoNewsPage extends BasePage {

    @FindBy(xpath = "//h1[contains(@class, 'main-header')]")
    private WebElement title;

    @FindBy(xpath = "//button[contains(@class, 'tag-button')]")
    private List<WebElement> tags;

    @FindBy(xpath = "//div[@id='create-button']")
    private WebElement createNewsButton;

    @FindBy(xpath = "//ul[contains(@class,'list gallery-view-active ng-star-inserted')]")
    private List<WebElement> newsCards;

    @Getter
    private List<NewsComponent> news;

    private final By createButtonBy = By.xpath("//span[contains(@id, 'create-button-text')]");


    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateEditNewsPage clickCreateNewsButton() {
        createNewsButton.click();
        return new CreateEditNewsPage(driver);
    }

    public NewsPage clickFirstNewsPage() {
        WebElement firstCard = newsCards.getFirst();
        firstCard.click();
        return new NewsPage(driver);
    }
    public WebElement getCreateButton() {
        return driver.findElement(createButtonBy);
    }
    public CreateEditNewsPage clickCreateButton() {
        getCreateButton().click();
        return new CreateEditNewsPage(driver);
    }

}
