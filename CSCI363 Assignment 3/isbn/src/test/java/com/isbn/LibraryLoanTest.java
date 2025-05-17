package com.isbn;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LibraryLoanTest {
    @Mock
    private Library library;
    private Loan loan;
    private Loan overdueloan;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loan = mock(Loan.class);
        library = new Library();
        when(loan.isOverdue()).thenReturn(false);
        when(loan.GetLoan()).thenReturn("User: Alice\n borrowed book: Fellowship + \ndue date: 25.03.2025");
        overdueloan = mock(Loan.class);
        when(overdueloan.isOverdue()).thenReturn(true);
        when(overdueloan.GetLoan()).thenReturn("User: Alice\n borrowed book: Fellowship + \ndue date: 15.03.2025");
    }

    @Test
    void testLoanBook() {
        library.loanBook(loan);
        assertFalse(library.getLoans().isEmpty());
    }

    @Test
    void testOverdueLoan() {
        library.getLoans().add(overdueloan);
        assertEquals(1, library.getLoans().stream().filter(Loan::isOverdue).count());
    }
}
