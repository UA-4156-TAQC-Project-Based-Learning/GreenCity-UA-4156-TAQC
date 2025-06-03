package com.greencity.ui.testrunners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestRunnerWithUser extends BaseTestRunner {

    @BeforeClass
    public void login() {

        localStorageJS.setItemLocalStorage("accessToken", testValueProvider.getLocalStorageAccessToken());
        localStorageJS.setItemLocalStorage("userId", testValueProvider.getLocalStorageUserId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.navigate().refresh();
    }

    @AfterClass
    public void cleanup() {
        localStorageJS.clearLocalStorage();
    }

}
