package ru.geekbrains.main.site.at;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import java.util.concurrent.TimeUnit;

public class NavigationTest extends BaseTest {

    @Test
    void navigationPanelCareerPageTest () {
        goToTargetPage("nav > a[href='/career']");
        testHeaderItem( ".gb-header__title", "Карьера");
        testFooterItemFeedbacks ("nav > a[href='/career']");
    }

    @Test
    void navigationPanelTestsPageTest () {
        goToTargetPage("nav > a[href='/tests']");
        testHeaderItem( ".gb-header__title", "Тесты");
        testFooterItemFeedbacks ("nav > a[href='/tests']");
    }

    @Test
    void navigationPanelCoursesPageTest () {
        goToTargetPage("nav > a[href='/courses']");
        testHeaderItem( ".gb-header__title", "Курсы");
        testFooterItemFeedbacks ("nav > a[href='/courses']");
    }

    @Test
    void navigationPanelEventsPageTest () {
        goToTargetPage("nav > a[href='/events']");
        testHeaderItem( ".gb-header__title", "Вебинары");
        testFooterItemFeedbacks ("nav > a[href='/events']");
    }

    @Test
    void navigationPanelForumPageTest () {
        goToTargetPage("nav > a[href='/topics']");
        testHeaderItem( ".gb-header__title", "Форум");
        testFooterItemFeedbacks("nav > a[href='/events']");
    }

    @Test
    void navigationPanelBlogPageTest () {
        goToTargetPage("nav > a[href='/posts']");
        testHeaderItem( ".gb-header__title", "Блог");
        testFooterItemFeedbacks("nav > a[href='/events']");
    }

    void goToTargetPage (String findElementCssSelector) {
        driver.get(BASE_URL + "/career");
        driver.findElement(By.cssSelector(findElementCssSelector)).click();
        if (findElementCssSelector.contains("courses")) {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//div/div/button[*]")).click();
        }
    }

    void testHeaderItem (String checkElementCssSelector, String dataVerify ) {
        String header = driver.findElement(By.cssSelector(checkElementCssSelector)).getText();
        Assertions.assertEquals(dataVerify, header);
    }

    void testFooterItemFeedbacks (String findElementCssSelector) {
        String header = driver.findElement(By.cssSelector("a[href='/feedbacks']")).getText();
        Assertions.assertEquals("Отзывы", header);
    }

}
