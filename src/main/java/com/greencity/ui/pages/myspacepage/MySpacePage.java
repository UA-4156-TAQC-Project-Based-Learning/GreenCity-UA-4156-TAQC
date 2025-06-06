package com.greencity.ui.pages.myspacepage;

import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class MySpacePage extends BasePage {

    @FindBy(xpath = "//div[@class='description']/div[2]")
    WebElement mySpaceDescription;

    public MySpacePage(WebDriver driver) {
        super(driver);
    }
}
