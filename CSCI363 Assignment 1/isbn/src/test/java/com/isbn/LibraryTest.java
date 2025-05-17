package com.isbn;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;

public class LibraryTest {
    @Mock
    private Library library;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        library = new Library();
    }

    @Test
    void testAddBook() throws IOException {
        Book book = new Book("0345339703");
        library.addBook(book);
        assertNotNull(library.findBookByIsbn("1234567890"));
    }

    @Test
    void testLoanBook() throws IOException {
        User user = new User("Alice", "U001");
        Book book = new Book("0345339703");
        Loan loan = new Loan(user, book, LocalDate.of(2025,3,25));
        library.registerUser(user);
        library.addBook(book);
        library.loanBook(loan);
        assertFalse(library.getLoans().isEmpty());
    }

    @Test
    void testOverdueLoans() throws IOException {
        User user = new User("Bob", "U002");
        Book book = new Book("0345339703");
        library.registerUser(user);
        library.addBook(book);
        Loan loan = new Loan(user, book, LocalDate.of(2025,3,13)); // overdue loan
        library.getLoans().add(loan);
        assertEquals(1, library.getLoans().stream().filter(Loan::isOverdue).count());
    }
}