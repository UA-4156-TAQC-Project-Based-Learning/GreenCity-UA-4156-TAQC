package com.greencity.ui.pages;

import com.greencity.ui.Base;
import com.greencity.ui.components.footer.FooterComponent;
import com.greencity.ui.components.header.HeaderComponent;
import com.greencity.ui.components.header.HeaderGuestUserComponent;
import com.greencity.ui.components.header.HeaderLoggedUserComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public abstract class BasePage extends Base {
    @Getter
    protected HeaderComponent header;

    @Getter
    protected HeaderGuestUserComponent headerGuestUser;

    @Getter
    protected HeaderLoggedUserComponent headerLoggedUser;

    @Getter
    protected FooterComponent footer;

    @FindBy(xpath = "//app-header")
    private WebElement headerRoot;
    @FindBy(xpath = "//footer")
    private WebElement FooterRoot;

    public BasePage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver, headerRoot);
        footer = new FooterComponent(driver, FooterRoot);
        guestOrLoggedIn();
    }

    public void guestOrLoggedIn() {
        boolean isGuest = driver.findElements(By.className("header_sign-in-link")).size() > 0;
        if (isGuest) {
            this.headerGuestUser = new HeaderGuestUserComponent(driver, headerRoot);
        } else {
            this.headerLoggedUser = new HeaderLoggedUserComponent(driver, headerRoot);
        }
    }


    private int getContentHeight() {
        return ((Number) Objects.requireNonNull(threadJs.executeScript("return document.body.scrollHeight;"))).intValue();
    }


    public void waitForPageToLoad(long timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public Boolean isElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }


}