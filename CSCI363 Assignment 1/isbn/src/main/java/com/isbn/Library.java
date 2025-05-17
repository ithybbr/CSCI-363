package com.isbn;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }
    
    public boolean removeBook(String isbn) {
        return books.removeIf(book -> book.getIsbn().equals(isbn));
    }
    
    public Book findBookByIsbn(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void loanBook(Loan loan) {
        loans.add(loan);
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Loan> overdueLoans(){
        return loans.stream().filter(Loan::isOverdue).toList();
    }
}
