package com.greencity.ui.pages.myspacepage;

import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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
        listOfNews = driver.findElements(newsListInMyNewsTab);
        listOfNews.getFirst().click();
        return new NewsPage(driver);
    }
}
