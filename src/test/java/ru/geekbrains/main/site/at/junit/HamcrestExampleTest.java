package ru.geekbrains.main.site.at.junit;

import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestExampleTest {

    @Test
    public void test01 () {
        int a =5;
        Object object = new Object();
        Object object1 = object;
        assertThat(a, equalTo(5));
        assertThat(object, notNullValue());
        assertThat(object, sameInstance(object1));
    }

    @Test
    public void test02 () {
        int a = 1;
        int b = 20;
        int c = 150;
        double d = 3.444;

        assertThat(a, lessThan(10));
        assertThat(c, greaterThan(100));
        assertThat(b, lessThanOrEqualTo(20));
        assertThat(d, closeTo(3.5, 0.01));
    }

    @Test
    public void test03 () {
        String a = "Moscow";
        String b = "moscow";

        assertThat(a, equalToIgnoringCase(b));
    }

    @Test
    public void test04 () {
        int a = 1;
        assertThat(a, allOf(
                lessThan(10),
                greaterThan(0)
        ));
    }


    @Test
    public void test05 () {
        int a = 120;
        assertThat(a, anyOf(
                lessThan(300),
                greaterThan(1000)
        ));
    }
}

