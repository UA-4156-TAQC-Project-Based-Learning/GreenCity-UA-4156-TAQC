package com.greencity.ui.components.header;

import com.greencity.ui.components.baseComponents.BaseComponent;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HeaderComponent extends BaseComponent {

    @FindBy(xpath = ".//img[@src='assets/img/logo.svg']")
    private WebElement logo;

//    @FindBy(css = "a[href='#/greenCity/news'].url-name")
    @FindBy(css = "ul[role='tablist'] a[href='#/news']")
    private WebElement ecoNewsLink;

    @FindBy(xpath = "//div[@class = 'header_navigation-menu']")
    private WebElement modalRoot;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public EcoNewsPage goToEcoNews() {
        waitUntilElementClickable(ecoNewsLink);
        ecoNewsLink.click();
        return new EcoNewsPage(driver);
    }
}