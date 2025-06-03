package com.greencity.ui.pages.econewspage;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.components.header.HeaderLoggedUserComponent;
import com.greencity.ui.pages.BasePage;
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

    @Getter
    private WebElement createButton;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
        HeaderLoggedUserComponent headerComponent = new HeaderLoggedUserComponent(driver, header.getRootElement());
        if (headerComponent.getLoggedUserDropdown().isDisplayed()){
            initCreateButton();
        }
    }

    public void initCreateButton() {
        this.createButton = driver.findElement(By.xpath(".//span[contains(@id, 'create-button-text')]"));
    }

    public void clickCreateButton() {
        createButton.click();
    }
}
