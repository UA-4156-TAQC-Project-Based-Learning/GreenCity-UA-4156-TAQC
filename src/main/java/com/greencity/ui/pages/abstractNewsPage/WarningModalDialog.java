package com.greencity.ui.pages.abstractNewsPage;

import com.greencity.ui.components.baseComponents.BaseComponent;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class WarningModalDialog extends BaseComponent {

    @FindBy(xpath = ".//button[contains(@class, 'secondary-global-button')]")
    private WebElement noButton;

    @FindBy(xpath = ".//button[contains(@class, 'primary-global-button')]")
    private WebElement yesButton;

    public WarningModalDialog(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public EcoNewsPage clickYesButton(){
        yesButton.click();
        return new EcoNewsPage(driver);
    }
}
