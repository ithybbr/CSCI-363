package com.isbn;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsbnTableTest {
    @Test
    void testValidIsbnWithHyphens() {
        Isbn isbn = new Isbn("0-306-40615-2");
        assertTrue(isbn.isValid());
    }

    @Test
    void testValidIsbnWithoutHyphens() {
        Isbn isbn = new Isbn("0306406152");
        assertTrue(isbn.isValid());
    }

    @Test
    void testValidIsbnWithX() {
        Isbn isbn = new Isbn("3-598-21507-X");
        assertTrue(isbn.isValid());
    }

    @Test
    void testInvalidChecksum() {
        Isbn isbn = new Isbn("0-321-14653-9");
        assertFalse(isbn.isValid());
    }

    @Test
    void testIncorrectChecksum() {
        Isbn isbn = new Isbn("0123156789");
        assertFalse(isbn.isValid());
    }

    @Test
    void testTooShortIsbn() {
        Isbn isbn = new Isbn("0-321-14653");
        assertFalse(isbn.isValid());
    }

    @Test
    void testTooLongIsbn() {
        Isbn isbn = new Isbn("0-321-14653-99");
        assertFalse(isbn.isValid());
    }

    @Test
    void testInvalidCharacterInIsbn() {
        Isbn isbn = new Isbn("0-321-1465A-9");
        assertFalse(isbn.isValid());
    }

    @Test
    void testLowercaseXIsInvalid() {
        Isbn isbn = new Isbn("0-321-14653-x");
        assertFalse(isbn.isValid());
    }

    @Test
    void testInvalidDoubleHyphen() {
        Isbn isbn = new Isbn("0-306-40615--2");
        assertFalse(isbn.isValid());
    }
}
