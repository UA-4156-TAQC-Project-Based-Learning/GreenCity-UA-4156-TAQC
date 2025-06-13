package com.greencity.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginUtil {

    @Step("Log in via LocalStorage")
    public static void loginViaLocalStorage(WebDriver driver, LocalStorageJS localStorageJS, TestValueProvider provider) {
        driver.get(provider.getBaseUIUrl());

        localStorageJS.setItemLocalStorage("language", "en");
        localStorageJS.setItemLocalStorage("accessToken", provider.getLocalStorageAccessToken());
        localStorageJS.setItemLocalStorage("refreshToken", provider.getLocalStorageRefreshToken());
        localStorageJS.setItemLocalStorage("userId", provider.getLocalStorageUserId());
        localStorageJS.setItemLocalStorage("name", provider.getLocalStorageName());

        driver.navigate().refresh();
    }
}
