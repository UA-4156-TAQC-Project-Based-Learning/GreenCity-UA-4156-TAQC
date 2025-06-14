package com.greencity.ui.components.baseComponents;

import com.greencity.ui.pages.myspacepage.MySpacePage;
import com.greencity.ui.pages.notificationPage.NotificationPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class UserDropdownComponent extends BaseComponent {
    @FindBy(xpath = ".//a[normalize-space()='Notifications']")
    private WebElement notifications;

    @FindBy(xpath = ".//a[normalize-space()='Sign out']")
    private WebElement signOut;

    @FindBy(xpath = ".//a[contains(@href, '#/greenCity/profile')]")
    private WebElement mySpace;

    public UserDropdownComponent(WebDriver driver, WebElement root) {
        super(driver, root);
    }

    @Step("Click 'Notifications' in dropdown")
    public NotificationPage goToNotifications() {
        waitUntilElementClickable(notifications);
        notifications.click();
        return new NotificationPage(driver);
    }

    @Step("Click 'My Space' in dropdown")
    public MySpacePage goToMySpace() {
        waitUntilElementClickable(mySpace);
        mySpace.click();
        return new MySpacePage(driver);
    }

    @Step("Click 'Sign out' in dropdown")
    public void signOut() {
        waitUntilElementClickable(signOut);
        signOut.click();
    }
}
