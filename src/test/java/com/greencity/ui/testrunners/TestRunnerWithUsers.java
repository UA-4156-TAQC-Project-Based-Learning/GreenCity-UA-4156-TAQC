package com.greencity.ui.testrunners;

import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;

public class TestRunnerWithUsers extends BaseTestRunner {

    @Step("Login As User in the Test Runner With Users")
    public void loginAsUser(String accessToken, String userId, String refreshToken, String name){
        localStorageJS.setItemLocalStorage("accessToken", accessToken);
        localStorageJS.setItemLocalStorage("userId", userId);
        localStorageJS.setItemLocalStorage("refreshToken", refreshToken);
        localStorageJS.setItemLocalStorage("name", name);
        driver.navigate().refresh();
    }

    @Step("Login As User A in the Test Runner With Users")
    public void loginAsUserA(){
        loginAsUser(testValueProvider.getLocalStorageAccessToken(),
                testValueProvider.getLocalStorageUserId(),
                testValueProvider.getLocalStorageRefreshToken(),
                testValueProvider.getLocalStorageName());
    }

    @Step("Login As User B in the Test Runner With Users")
    public void loginAsUserB(){
        loginAsUser(testValueProvider.getLocalStorageAccessTokenUserB(),
                testValueProvider.getLocalStorageUserIdUserB(),
                testValueProvider.getLocalStorageRefreshTokenUserB(),
                testValueProvider.getLocalStorageNameUserB());
    }

    @Step("clean Local Storage in the Test Runner With Users")
    public void cleanLocalStorage(){
        localStorageJS.clearLocalStorage();
        driver.navigate().refresh();
    }

    @AfterClass
    public void cleanup() {
        cleanLocalStorage();
    }
}

