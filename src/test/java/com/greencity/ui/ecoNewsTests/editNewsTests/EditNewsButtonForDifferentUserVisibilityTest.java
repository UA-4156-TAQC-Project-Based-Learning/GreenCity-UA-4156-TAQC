package com.greencity.ui.ecoNewsTests.editNewsTests;

import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUsers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditNewsButtonForDifferentUserVisibilityTest extends TestRunnerWithUsers {

    @BeforeClass
    public void setup(){
        loginAsUserA();

        CreateEditNewsPage createEditNewsPage = homePage.getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();

        createEditNewsPage
                .enterTitle("Test News")
                .enterContent("Dishonored 2 is a 2016 first person action-adventure game developed by Arkane Lyon and published by Bethesda Softworks for PlayStation 4, Windows, and Xbox One." +
                        " It is the sequel to 2012's Dishonored." +
                        " After Empress Emily Kaldwin is deposed by the witch Delilah Copperspoon," +
                        " the player may choose between playing as either Emily or her father/Royal Protector Corvo Attano as they attempt to reclaim the throne.")
                .clickTag(NewsTags.EDUCATION_TAG)
                .clickPublish();

        cleanLocalStorage();
        loginAsUserB();
    }

    @Test
    public void verifyEditButtonIsNotVisibleToOtherUser(){
        NewsPage newsPage = homePage
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage();

        Assert.assertFalse(newsPage.isEditNewsButtonVisible(),
                "Edit button displayed even with another user logged in");

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        cleanLocalStorage();
        loginAsUserA();
        homePage
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage()
                .clickDeleteButton()
                .clickYesButton();
        Thread.sleep(3000);
    }
}
