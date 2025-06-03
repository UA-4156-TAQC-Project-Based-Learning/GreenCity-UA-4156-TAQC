package com.greencity.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LocalStorageJS {
    private JavascriptExecutor js;

    public LocalStorageJS(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public void setItemLocalStorage(String key, String value) {
        js.executeScript(String.format("window.localStorage.setItem('%s', '%s');", key, value));
    }
    public void clearLocalStorage() {
        js.executeScript("window.localStorage.clear();");
    }
}
