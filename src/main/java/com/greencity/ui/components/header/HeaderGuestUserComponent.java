package com.greencity.ui.components.header;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderGuestUserComponent extends HeaderComponent {

    @Getter
    @FindBy(className = "header_sign-in-link")
    private WebElement signInButton;

    public HeaderGuestUserComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void goToSignIn() {
        waitUntilElementClickable(signInButton);
        signInButton.click();
    }
}
