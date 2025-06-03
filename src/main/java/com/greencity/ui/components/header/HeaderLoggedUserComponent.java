package com.greencity.ui.components.header;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderLoggedUserComponent extends HeaderComponent {

    @Getter
    @FindBy(className = "nav-global-button")
    private WebElement loggedUserDropdown;

    public HeaderLoggedUserComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickUserDropdown() {
        waitUntilElementClickable(loggedUserDropdown);
        loggedUserDropdown.click();
    }
}
