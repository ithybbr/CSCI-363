package com.isbn;

import java.time.LocalDate;

public class Loan {
    private final User user;
    private final Book book;
    private final LocalDate dueDate;

    public Loan(User user, Book book, LocalDate dueDate) {
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate);
    }
    public String GetLoan(){
        return "User: " + this.user + "\nborrowed book:" + this.book + "\ndue date:" + this.dueDate;
    }
}
