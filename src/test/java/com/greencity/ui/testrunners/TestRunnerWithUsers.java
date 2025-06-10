package com.greencity.ui.testrunners;

import org.testng.annotations.AfterClass;

public class TestRunnerWithUsers extends BaseTestRunner {

    public void loginAsUser(String accessToken, String userId, String refreshToken, String name){
        localStorageJS.setItemLocalStorage("accessToken", accessToken);
        localStorageJS.setItemLocalStorage("userId", userId);
        localStorageJS.setItemLocalStorage("refreshToken", refreshToken);
        localStorageJS.setItemLocalStorage("name", name);
        driver.navigate().refresh();
    }

    public void loginAsUserA(){
        loginAsUser(testValueProvider.getLocalStorageAccessToken(),
                testValueProvider.getLocalStorageUserId(),
                testValueProvider.getLocalStorageRefreshToken(),
                testValueProvider.getLocalStorageName());
    }

    public void loginAsUserB(){
        loginAsUser(testValueProvider.getLocalStorageAccessTokenUserB(),
                testValueProvider.getLocalStorageUserIdUserB(),
                testValueProvider.getLocalStorageRefreshTokenUserB(),
                testValueProvider.getLocalStorageNameUserB());
    }

    public void cleanLocalStorage(){
        localStorageJS.clearLocalStorage();
        driver.navigate().refresh();
    }

    @AfterClass
    public void cleanup() {
        cleanLocalStorage();
    }
}

