package com.greencity.ui.pages.notificationPage;

import com.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NotificationPage extends BasePage {

    @FindBy(css = ".notification-wrapper")
    private WebElement notificationsContainer;

    @FindBy(css = ".no-notifications")
    private WebElement noNotificationsBlock;

    @FindBy(css = ".notification-item")
    private List<WebElement> notificationItems;

    @FindBy(xpath = "//h1[text()='Notifications']")
    private WebElement pageTitle;

    public NotificationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check if 'No Notifications' message is displayed")
    public boolean isNoNotificationsMessageDisplayed() {
        try {
            return noNotificationsBlock.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    @Step("Check if Notifications title is displayed")
    public boolean isTitleDisplayed() {
        try {
            return pageTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Get number of notification items")
    public int getNotificationCount() {
        return notificationItems.size();
    }
}
