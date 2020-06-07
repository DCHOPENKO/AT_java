package ru.geekbrains.main.site.at.junit;


import org.junit.jupiter.api.*;

import java.util.Random;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class AnnotationExampleTest {

    @Test
    @DisplayName("Трушный тест")
    public void test01 () {
        assertTrue(true);
    }

    @Disabled
    @Test
    public void test02 () {
        assertTrue(true);
    }

    @RepeatedTest(200)
    @DisplayName("Проверка: число больше 50")
    @Test
    public void test03 () {
        int x = new Random().nextInt(100);
        assertTrue(x>50);
    }

    @Timeout(1)
    @DisplayName("падаем по тайм ауту")
    @Test
    public void test04 () throws InterruptedException {
        sleep(1100);
        assertTrue(true);
    }

}
