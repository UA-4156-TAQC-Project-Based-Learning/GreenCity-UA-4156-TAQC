package com.greencity.cucumber.hooks;

import com.greencity.utils.FileNameNormalizer;
import com.greencity.utils.LocalStorageJS;
import com.greencity.utils.TestValueProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Hooks {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static String PathImageTestIsFailed;
    @Getter
    private WebDriver driver;
    @Getter
    protected LocalStorageJS localStorageJS;

    @Getter
    private final TestValueProvider testValueProvider = new TestValueProvider();

    @Getter
    private SoftAssert softAssert;

    @BeforeAll
    public static void beforeAll() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");

        PathImageTestIsFailed = "target/imageTestIsFailed/" + currentDateTime.format(formatter);
        try {
            Files.createDirectories(Path.of(PathImageTestIsFailed));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void driverSetup() {
        if (driver == null) {
            initDriver();
        }
        logger.info("WebDriver started");

        softAssert = new SoftAssert();
        driver.get(testValueProvider.getBaseUIUrl());
    }

    @Step("init ChromeDriver")
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

//        for (String option : testValueProvider.getChromeOptions()) {
//            options.addArguments(option);
//        }

//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");
//        options.addArguments("--user-data-dir=" + testValueProvider.getUserProfile().replace("%HOMEPATH%", System.getenv("HOMEPATH")));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));
        localStorageJS = new LocalStorageJS(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            saveImageAttach(scenario.getName());
        }
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver closed");

        softAssert.assertAll();
    }

    @Attachment(value = "Image name = {0}", type = "image/png")
    public byte[] saveImageAttach(String attachName) {
        attachName = FileNameNormalizer.normalizeFileName(attachName);
        byte[] result = null;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            result = Files.readAllBytes(scrFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = PathImageTestIsFailed + "/" + attachName + ".png";
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(result);
            logger.info("Screenshot saved to {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to save screenshot to {}", filePath, e);
        }
        return result;
    }

    public void loginViaLocalStorage() {
        // This step can be implemented to ensure the user is registered and logged in.
        // For example, you might navigate to the login page, enter credentials, and submit the form.
        driver.get(testValueProvider.getBaseUIUrl());
        localStorageJS.setItemLocalStorage("accessToken", testValueProvider.getLocalStorageAccessToken());
        localStorageJS.setItemLocalStorage("userId", testValueProvider.getLocalStorageUserId().toString());
        localStorageJS.setItemLocalStorage("refreshToken", testValueProvider.getLocalStorageRefreshToken());
        localStorageJS.setItemLocalStorage("name", testValueProvider.getLocalStorageName());
        localStorageJS.setItemLocalStorage("language", testValueProvider.getLocalStorageLanguage());
        driver.navigate().refresh();
    }
}