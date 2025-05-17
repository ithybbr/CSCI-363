package com.isbn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryUserTest {
    @Mock
    private Library library;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = mock(User.class);
        library = new Library();
        when(user.getUserId()).thenReturn("U001");
        when(user.getName()).thenReturn("Alice");
    }

    @Test
    void testRegisterUser() {
        library.registerUser(user);
        assertNotNull(library.getUsers());
    }
}
