package com.greencity.ui.components.footer;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterComponent extends BaseComponent {

    @Getter
    @FindBy(css = "app-footer img.logo")
    private WebElement logo;

    @Getter
    @FindBy(xpath = "//app-footer//a[contains(@href, 'news')]")
    private WebElement ecoNewsLink;

    @Getter
    @FindBy(xpath = "//app-footer//a[contains(@href, 'events')]")
    private WebElement eventsLink;

    @Getter
    @FindBy(xpath = "//app-footer//a[contains(@href, 'places')]")
    private WebElement placesLink;

    @Getter
    @FindBy(xpath = "//app-footer//a[contains(@href, 'about')]")
    private WebElement aboutUsLink;

    @Getter
    @FindBy(xpath = "//app-footer//a[contains(@href, 'profile')]")
    private WebElement mySpaceLink;

    @Getter
    @FindBy(xpath = "//app-footer//a[contains(@href, 'ubs')]")
    private WebElement ubsCourierLink;

    @Getter
    @FindBy(css = "app-footer li.footer_follow-us")
    private WebElement followUsLabel;

    @Getter
    @FindBy(xpath = "//app-footer//img[@alt='Twitter link']")
    private WebElement twitterLink;

    @Getter
    @FindBy(xpath = "//app-footer//img[@alt='LinkedIn link']")
    private WebElement linkedInLink;

    @Getter
    @FindBy(xpath = "//app-footer//img[@alt='Facebook link']")
    private WebElement facebookLink;

    @Getter
    @FindBy(xpath = "//app-footer//img[@alt='Instagram link']")
    private WebElement instagramLink;

    @Getter
    @FindBy(xpath = "//app-footer//img[@alt='YouTube link']")
    private WebElement youTubeLink;
    public FooterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}