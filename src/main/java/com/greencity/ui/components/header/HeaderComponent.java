package com.greencity.ui.components.header;

import com.greencity.ui.components.baseComponents.BaseComponent;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//img[@src='assets/img/logo.svg']")
    private WebElement logo;
    @Getter
    @FindBy(css = "ul[role='tablist'] a[href='#/news']")
    private WebElement ecoNewsLink;


    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public EcoNewsPage goToEcoNews() {
        waitUntilElementClickable(ecoNewsLink);
        ecoNewsLink.click();
        return new EcoNewsPage(driver);
    }
}