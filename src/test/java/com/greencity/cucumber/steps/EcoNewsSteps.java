package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class EcoNewsSteps {
    private final Hooks hooks;

    private EcoNewsPage ecoNewsPage;

    public EcoNewsSteps(Hooks hooks ) {
        this.hooks = hooks;
    }

    private EcoNewsPage getEcoNewsPage() {
        return new EcoNewsPage(hooks.getDriver());
    }

    @And("click the Create news")
    public void clickCreateNews(){
        getEcoNewsPage().clickCreateNewsButton();
    }

    @Then("the news article with title {string}, text {string}, and tag {string} should be saved in the eco-news page")
    public void verifyNewsIsVisible(String expectedTitle, String expectedText, String expectedTag) {

       NewsComponent createdNews =  getEcoNewsPage().findNewsByTitle(expectedTitle);

       hooks.getSoftAssert().assertEquals(createdNews.getTitle().getText(), expectedTitle, "The title should be the same");
       hooks.getSoftAssert().assertEquals(createdNews.getContent().getText(), expectedText, "The text should be the same");
       hooks.getSoftAssert().assertEquals(createdNews.getTag().getText(),expectedTag,"The tag should be the same");
    }

    @And("click on their own news article with title {string}")
    public void clickOwnNews(String title){
        getEcoNewsPage().findNewsByTitleAndClick(title);
    }
}
