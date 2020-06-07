package ru.geekbrains.main.site.at.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParametrizedExampleTest {


    @ParameterizedTest
    @ValueSource(strings = {"Coursed","Tests"})
    public void test01 (String title) {
        assertNotNull(title);
    }

    @ParameterizedTest
    @MethodSource("stringGenerator")
    public void test02 (String title) {
        assertNotNull(title);
    }

    public static Stream<String> stringGenerator () {
        return Stream.of("Events","Blocks",null);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test03_source.csv", numLinesToSkip = 1)
    public void test03 (int a, int b, int result) {
        assertEquals(result, a+b);
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void test04 (int a, int b, int result) {
        assertEquals(result, a+b);
    }

    public static Stream<Arguments> arguments () {
        return Stream.of (
                Arguments.of(1,1,2),
                Arguments.of(0,0,0),
                Arguments.of(12,-13,-1)
        );
    }
}
