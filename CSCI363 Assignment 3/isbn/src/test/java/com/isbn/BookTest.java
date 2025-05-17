package com.isbn;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;

public class BookTest {
    @Mock
    private Isbn isbn;
    private Book book;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        isbn = mock(Isbn.class);
        when(isbn.isValid()).thenReturn(true);
        when(isbn.toString()).thenReturn("0-345-33970-3");
        when(isbn.getFormattedIsbn()).thenReturn("0345339703");
        book = new Book(isbn);
    }
    @Test
    void testBook() throws IOException {
        assertNotNull(book);
        assertEquals(isbn.getFormattedIsbn(), book.getFormattedIsbn());
        assertEquals(book.toString(), "Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", ISBN: " + book.getIsbn());
    }
}