package com.isbn;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

public class LibraryTest {
    @Mock
    private Library library;
    private Book book;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        book = mock(Book.class);
        library = new Library();
        when(book.getIsbn()).thenReturn("0345339703");
        when(book.getTitle()).thenReturn("The Fellowship of the Ring");
        when(book.getAuthor()).thenReturn("J.R.R. Tolkien");
    }
    @Test
    void testAddBook() throws IOException {
        library.addBook(book);
        assertNotNull(library.findBookByIsbn("0345339703"));
    }

    @Test
    void testRemoveBook() throws IOException {
        library.addBook(book);
        assertTrue(library.removeBook("0345339703"));
    }
}