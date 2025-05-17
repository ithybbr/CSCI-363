package com.isbn;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.Mockito.*;

public class BookInvalidTest {
    @Test
    void testInvalidIsbn() {
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    void testNullOrEmptyIsbn(String invalidIsbn) {
    }
}
