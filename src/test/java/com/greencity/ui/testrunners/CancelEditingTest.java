package com.greencity.ui.testrunners;

import com.greencity.ui.components.baseComponents.CancelConfirmModal;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CancelEditingTest extends TestRunnerWithUser {
    @Test
    public void declineModifyTitle() {
        driver.get("http://localhost:4205/#/greenCity/news");
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        String originalNewsTitle = ecoNewsPage.getTitle().getText();

        ecoNewsPage.clickCreateNewsButton();

        CreateEditNewsPage createEditNewsPage = new CreateEditNewsPage(driver);
        createEditNewsPage.enterTitle("New temporary title");

        try {
            Thread.sleep(2000); // 2 секунды, чтобы ты успела увидеть заголовок
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        createEditNewsPage.clickCancel();

        CancelConfirmModal cancelConfirmModal = CancelConfirmModal.waitForModal(driver);
        cancelConfirmModal.clickYesCancel();

        String currentNewsTitle = ecoNewsPage.getTitle().getText();


        Assert.assertEquals(originalNewsTitle,currentNewsTitle, "New title is changed after decline editing");

    }
}
