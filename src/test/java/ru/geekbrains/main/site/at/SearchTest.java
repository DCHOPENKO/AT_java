package ru.geekbrains.main.site.at;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchTest extends BaseTest {

    @BeforeAll
    public static void setUpVeforeAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        searchRequest("java", 15);
    }

    @AfterAll
    public static void tearDownAfterAll() {
        driver.quit();
    }

    @Override
    @BeforeEach
    public void setUp() {
    }

    @Override
    @AfterEach
    public void tearDown() {
    }

    @ParameterizedTest
    @DisplayName("Проверка поискового запроса / проверка отображения инф. о найденных блоках (название и кол-во))")
    @CsvFileSource(resources = "/SearchTest_source_data.csv", numLinesToSkip = 1)
    void postsProfessionsForSearchTest(String name, String cssSelector) {
        blockIsDisplayed(cssSelector);
        checkValueCondition(cssSelector);
    }

    public void checkValueCondition(String title) {
        int value = verifyQuantityPosts(title);
        if (title.contains("professions")) {
            assertThat(value, greaterThanOrEqualTo(2));
        } else if (title.contains("courses")) {
            assertThat(value, greaterThan(15));
        } else if (title.contains("webinars")) {
            assertThat(value, allOf(
                    greaterThan(180),
                    lessThan(300)
            ));
        } else if (title.contains("blogs")) {
            assertThat(value, greaterThan(300));
        } else if (title.contains("forums")) {
            assertThat(value, not(equalTo(350)));
        } else if (title.contains("tests")) {
            assertThat(value, not(equalTo(0)));
        } else if (title.contains("companies")) {
            assertThat(value, not(equalTo(0)));
        }
    }

    public static void searchRequest(String searchRequest, int waitingTimeInSec) {
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
