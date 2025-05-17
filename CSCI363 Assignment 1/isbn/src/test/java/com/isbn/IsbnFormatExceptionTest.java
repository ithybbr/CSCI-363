package com.isbn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class IsbnFormatExceptionTest {
    @Test
    void isValid_InvalidSymbols_IsbnIsNotValid() {
        Isbn validator1 = new Isbn("----3-5--98-21508-8");
        Isbn validator2 = new Isbn("359821507--XX");
        Isbn validator3 = new Isbn("359X821507--X");
        assertFalse(validator1.isValid());
        assertFalse(validator2.isValid());
        assertFalse(validator3.isValid());
    }
    
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "    "})
    void isValid_StringIsNullOrEmptyOrWhiteSpace_ThrowIllegalArgumentException(String number) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Isbn(number));
        assertEquals("String cannot be null, empty, or whitespace.", thrown.getMessage());
    }
}
