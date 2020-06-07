package ru.geekbrains.main.site.at.junit;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;


public class AssertionsExampleTest {

    @Test
    public void test01 () {
        assertEquals(1,1,"трушный результат");
    }

    @Test
    public void test02 () {
        int [] a = {3,4,5};
        int [] b = {3,4,5};
        assertArrayEquals(a,b,"arrays are equal");
    }

    @Test
    public void test03 () {
        String a = null;
        String b = "some text";
        assertNotNull(b);
        assertNull(a);
    }

    @Test
    public void test04 () {
        List<Integer> list = Arrays.asList(1, 4, 5);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.get(5);
        });
    }

    @Test
    public void test05 () {
        assertTimeout(Duration.ofMillis(800), () -> {
                sleep(500);
        });
    }
}
