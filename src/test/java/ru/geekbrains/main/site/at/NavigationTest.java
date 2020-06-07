package ru.geekbrains.main.site.at;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;


import java.util.concurrent.TimeUnit;


public class NavigationTest extends BaseTest {

    @ParameterizedTest
    @DisplayName("Проверка левой навигационной панели")
    @CsvFileSource(resources = "/navigationTest_source_data.csv", numLinesToSkip = 1)
    public void navigationPanelTesting(String checkHeaderDataVerify, String findElementCssSelector,
                                       String checkHeaderElementCssSelector,
                                       String checkFooterElementCssSelector) {
        goToTargetPage(findElementCssSelector);
        testHeaderItem(checkHeaderElementCssSelector, checkHeaderDataVerify);
        testFooterItemFeedbacks(checkFooterElementCssSelector);
    }


    void goToTargetPage(String findElementCssSelector) {
        driver.get(BASE_URL + "/career");
        driver.findElement(By.cssSelector(findElementCssSelector)).click();
        if (findElementCssSelector.contains("courses")) {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//div/div/button[*]")).click();
        }
    }

    void testHeaderItem(String checkElementCssSelector, String dataVerify) {
        String header = driver.findElement(By.cssSelector(checkElementCssSelector)).getText();
        Assertions.assertEquals(dataVerify, header);
    }

    void testFooterItemFeedbacks(String findElementCssSelector) {
        String header = driver.findElement(By.cssSelector("a[href='/feedbacks']")).getText();
        Assertions.assertEquals("Отзывы", header);
    }


}
