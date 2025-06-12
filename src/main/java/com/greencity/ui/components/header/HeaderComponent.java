package com.greencity.ui.components.header;

import com.greencity.ui.components.baseComponents.BaseComponent;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.myspacepage.MySpacePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HeaderComponent extends BaseComponent {

    @FindBy(xpath = ".//img[@src='assets/img/logo.svg']")
    private WebElement logo;

// @FindBy(css = "ul[role='tablist'] a[href='#/news']")
    @FindBy(css = "a[href='#/greenCity/news'].url-name")
    private WebElement ecoNewsLink;

    @FindBy(xpath = ".//a[contains(@href, '#/greenCity/profile')]")
    private WebElement mySpaceLink;

    private final String mySpaceLinkXPath = ".//a[contains(@href, '#/greenCity/profile')]";

    @FindBy(xpath = ".//div[@class = 'header_navigation-menu']")
    private WebElement modalRoot;

    @FindBy(xpath = ".//li[contains(@class,'user-name')]")
    private WebElement loggedInUserName;

    public String getLoggedInUserName() {
        return loggedInUserName.getText().trim();
    }

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Navigate to Eco News page")
    public EcoNewsPage goToEcoNews() {
        waitUntilElementClickable(ecoNewsLink);
        ecoNewsLink.click();
        return new EcoNewsPage(driver);
    }

    @Step("Click My Space link in the header")
    public MySpacePage goToMySpace() {
        try {
            waitUntilElementClickable(mySpaceLink);
            mySpaceLink.click();
        } catch (StaleElementReferenceException e) {
            WebElement newMySpaceLink = driver.findElement(By.xpath(getMySpaceLinkXPath()));
            waitUntilElementClickable(newMySpaceLink);
            newMySpaceLink.click();
        }
        return new MySpacePage(driver);
    }

}