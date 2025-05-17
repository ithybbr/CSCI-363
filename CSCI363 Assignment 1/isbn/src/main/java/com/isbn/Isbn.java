package com.isbn;

public class Isbn {
    private final String isbn;
    
    public Isbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("String cannot be null, empty, or whitespace.");
        }
        this.isbn = isbn;
    }

    public boolean isValid() {
        return validateIsbn(isbn);
    }

    private boolean validateIsbn(String isbn) {
        if (isbn.length() >= 12) {
            if(!isValidHyphenation(isbn)) return false;
        }

        String cleanedIsbn = isbn.replace("-", "").trim();
        if (cleanedIsbn.length() != 10) return false;

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            char c = cleanedIsbn.charAt(i);
            int value;
            if (Character.isDigit(c)) value = Character.getNumericValue(c);
            else if (c == 'X' && i == cleanedIsbn.length() - 1) value = 10;
            else return false;

            sum += value * (10 - i);
        }

        return sum % 11 == 0;
    }

    private boolean isValidHyphenation(String isbn) {
        if(isbn.charAt(1) != '-' || isbn.charAt(5) != '-' || (isbn.length() != 13 && isbn.length() != 12)) return false;
        return true;
    }

    public String getFormattedIsbn() {
        return this.isbn.replaceAll("[^0-9X]", "");
    }
}
