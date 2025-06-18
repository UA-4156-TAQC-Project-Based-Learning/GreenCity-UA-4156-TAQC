package com.greencity.ui.testrunners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestRunnerWithUser extends BaseTestRunner {

    @BeforeClass
    public void login() {
        localStorageJS.setItemLocalStorage("accessToken", testValueProvider.getLocalStorageAccessToken());
        localStorageJS.setItemLocalStorage("userId", testValueProvider.getLocalStorageUserId().toString());
        localStorageJS.setItemLocalStorage("refreshToken", testValueProvider.getLocalStorageRefreshToken());
        localStorageJS.setItemLocalStorage("name", testValueProvider.getLocalStorageName());
        driver.navigate().refresh();
    }

    @AfterClass
    public void cleanup() {
        localStorageJS.clearLocalStorage();
    }

}
