package com.greencity.ui.pages.homepage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInModal {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//ul[contains(@class, 'header_lang')]//li[@role='option' and contains(@aria-label, 'english')]")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//span[text()='En']")
    private WebElement englishOption;

    @FindBy(xpath = "//img[contains(@class, 'ubs-header-sing-in-img') and @alt='sing in button']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='email' and @type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='password' and @type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class, 'greenStyle') and @type='submit']")
    private WebElement signInSubmitButton;

    public SignInModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void switchToEnglish() {
        wait.until(ExpectedConditions.elementToBeClickable(languageSwitcher)).click();
        wait.until(ExpectedConditions.elementToBeClickable(englishOption)).click();
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton)).click();
    }
}
