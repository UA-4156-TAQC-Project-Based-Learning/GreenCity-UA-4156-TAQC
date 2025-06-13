package com.greencity.ui;

import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SourceFieldValidationTest extends TestRunnerWithUser {
    @Issue("18")
    @Owner("Yuliia Terentieva")
    @Description("Verify the validation of the Source field (optional, must be a valid URL).")
    @Feature("Create News")
    @Test
    public void sourceFieldValidation() {
        driver.get(testValueProvider.getBaseUIUrl() + "/profile");
        String currentTitle = homePage
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .enterTitle("Source test")
                .clickTag(NewsTags.EDUCATION_TAG)
                .enterContent("Source test content text to 20 chars")
                .clickPublish()
                .getNewsComponents()
                .getFirst()
                .getTitle()
                .getText();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(currentTitle, "Source test","Current title is not as expected.");

        CreateEditNewsPage createEditNewsPage = new EcoNewsPage(driver)
                //org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element <div _ngcontent-ng-c3257706870="" id="create-button" class="secondary-global-button m-btn">...</div> is not clickable at point (1140, 15).
                // Other element would receive the click: <li _ngcontent-ng-c113581887="" role="option" aria-label="english" tabindex="0" class="lang-option">...</li>
                .clickCreateNewsButton()
                .enterTitle("Source test with source")
                .clickTag(NewsTags.EDUCATION_TAG)
                .enterContent("Source test content text to 20 chars")
                .enterSource("www.example.com");


        softAssert.assertTrue(createEditNewsPage.getSourceErrorMessage().isDisplayed());
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled());

        createEditNewsPage.enterSource("https://example.com").clickPublish();
        String withSourceTitle = new EcoNewsPage(driver).getNewsComponents().getFirst().getTitle().getText();
        softAssert.assertEquals(withSourceTitle, "Source test with source","Current title is not as expected.");

        softAssert.assertAll();
    }
}
