package ru.geekbrains.main.site.at;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchTest extends BaseTest {

    @Test
    void postsProfessionsForSearchTest() {
        searchRequest("java", 15);
        blockIsDisplayed("ul.search-page-tabs > li > a[data-tab='professions']");
        int value = verifyQuantityPosts("ul.search-page-tabs > li > a[data-tab='professions']");
        Assertions.assertTrue(value >= 2);
    }

    @Test
    void postsCoursesForSearchTest() {
        searchRequest("java", 15);
        blockIsDisplayed("ul.search-page-tabs > li > a[data-tab='courses']");
        int value = verifyQuantityPosts("ul.search-page-tabs > li > a[data-tab='courses']");
        Assertions.assertTrue(value > 15);
    }

    @Test
    void postsWebinarsForSearchTest() {
        searchRequest("java", 15);
        blockIsDisplayed("ul.search-page-tabs > li > a[data-tab='webinars']");
        int value = verifyQuantityPosts("ul.search-page-tabs > li > a[data-tab='webinars']");
        Assertions.assertTrue(value > 180 && value < 300);
    }

    @Test
    void postsBlogsForSearchTest() {
        searchRequest("java", 15);
        blockIsDisplayed("ul.search-page-tabs > li > a[data-tab='blogs']");
        int value = verifyQuantityPosts("ul.search-page-tabs > li > a[data-tab='blogs']");
        Assertions.assertTrue(value > 300);
    }

    @Test
    void postsForumsForSearchTest() {
        searchRequest("java", 15);
        blockIsDisplayed("ul.search-page-tabs > li > a[data-tab='forums']");
        int value = verifyQuantityPosts("ul.search-page-tabs > li > a[data-tab='forums']");
        Assertions.assertTrue(value != 350);
    }

    @Test
    void postsTestsForSearchTest() {
        searchRequest("java", 15);
        blockIsDisplayed("ul.search-page-tabs > li > a[data-tab='tests']");
        int value = verifyQuantityPosts("ul.search-page-tabs > li > a[data-tab='tests']");
        Assertions.assertTrue(value != 0);
    }

    @Test
    void postsCompaniesForSearchTest() {
        searchRequest("java", 15);
        blockIsDisplayed("ul.search-page-tabs > li > a[data-tab='companies']");
        int value = verifyQuantityPosts("ul.search-page-tabs > li > a[data-tab='companies']");
        Assertions.assertTrue(value != 0);
    }

    void searchRequest (String searchRequest, int waitingTimeInSec) {
        driver.get(BASE_URL + "/courses");
        driver.findElement(By.xpath("//div/div/button[*]")).click();
        driver.findElement(By.cssSelector("ul > li > .show-search-form")).click();
        driver.findElement(By.cssSelector(".search-panel__search-field")).sendKeys(searchRequest);
        WebDriverWait wait = new WebDriverWait(driver, waitingTimeInSec);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.search-page-tabs")));
    }

    void blockIsDisplayed (String cssSelector) {
        WebElement element = driver
                .findElement(By.cssSelector(cssSelector));
        Assertions.assertTrue(element.isDisplayed());
    }

    int  verifyQuantityPosts (String cssSelector) {
        String text = driver.findElement(By.cssSelector(cssSelector)).getText();
        String[] array = text.split("・");
        return Integer.parseInt(array[array.length - 1]);
    }

}
