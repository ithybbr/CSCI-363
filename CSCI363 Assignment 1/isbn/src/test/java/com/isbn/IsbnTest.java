package com.isbn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class IsbnTest {
    @Test
    void isValid_IsbnIsValid() {
        Isbn validator1 = new Isbn("3-598-21508-8");
        Isbn validator2 = new Isbn("3-598-21507-X");
        Isbn validator3 = new Isbn("3-598-215088");
        Assertions.assertTrue(validator1.isValid());
        Assertions.assertTrue(validator2.isValid());
        Assertions.assertTrue(validator3.isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"3-598-21508-9", "3-598-21507-A", "00", "3-598-21507"})
    void isValid_IsbnIsNotValid(String number) {
        Isbn validator = new Isbn(number);
        assertFalse(validator.isValid());
    }
    
    @ParameterizedTest
    @CsvSource({
    "3-598-21508-8, 3598215088",
    "3-598-21507-X, 359821507X",
    "3598215088, 3598215088"
    })
    void assertExpectedTest(String number, String expected) {
        Isbn validator = new Isbn(number);
        assertEquals(expected, validator.getFormattedIsbn());
    }
}

