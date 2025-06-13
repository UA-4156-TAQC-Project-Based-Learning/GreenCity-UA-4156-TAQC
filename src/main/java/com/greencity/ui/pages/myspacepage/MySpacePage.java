package com.greencity.ui.pages.myspacepage;

import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

@Getter
public class MySpacePage extends BasePage {

    @FindBy(xpath = "//div[@class='description']/div[2]")
    private WebElement mySpaceDescription;

    @FindBy(xpath = "//div[contains(@id, 'mat-tab-label')]")
    private List<WebElement> matTabs;

    private final By newsListInMyNewsTab = By.xpath("//app-one-news");
    private List<WebElement> listOfNews;


    public MySpacePage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on {name} tab")
    public MySpacePage clickTabsByText(String name){
        for (WebElement tab : matTabs) {
            if (tab.getText().contains(name)){
                tab.click();
                break;
            }
        }
        return this;
    }

    public NewsPage clickFirstNew(){
        if (listOfNews.isEmpty()) {
            throw new NoSuchElementException("No news found in 'My news' tab");
        }
        listOfNews.get(0).click();
        return new NewsPage(driver);
    }
}
