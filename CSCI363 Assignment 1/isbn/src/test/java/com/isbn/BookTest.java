package com.isbn;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class BookTest {

    @ParameterizedTest
    @CsvSource({
        "0345339703, The Fellowship of the Ring, J.R.R. Tolkien",
        "1781100500, Harry Potter and the Chamber of Secrets, J.K. Rowling",
        "0061120081, To Kill a Mockingbird, Harper Lee"
    })
    void testValidBookRetrieval(String isbn, String expectedTitle, String expectedAuthor) {
        Book book;
        try {
            book = new Book(isbn);
            assertNotNull(book);
            assertEquals(expectedTitle, book.getTitle());
            assertEquals(expectedAuthor, book.getAuthor());
            assertEquals(isbn, book.getIsbn());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RepeatedTest(5)
    void testRepeated() {
        Book book;
        try {
            book = new Book("0345339703");
            assertNotNull(book);
            assertEquals("The Fellowship of the Ring", book.getTitle());
            assertEquals("J.R.R. Tolkien", book.getAuthor());
            assertEquals("0345339703", book.getIsbn());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({
        "0345339703, The Fellowship of the Ring, J.R.R. Tolkien",
        "1781100500, Harry Potter and the Chamber of Secrets, J.K. Rowling",
        "0061120081, To Kill a Mockingbird, Harper Lee"
    })
    void testToStringMethod(String isbn, String expectedTitle, String expectedAuthor) {
        Book book;
        try {
            book = new Book(isbn);
            String expectedToString = String.format("Title: %s, Author: %s, ISBN: %s", expectedTitle, expectedAuthor, isbn);
            assertEquals(expectedToString, book.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
