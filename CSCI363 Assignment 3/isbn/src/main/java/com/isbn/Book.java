package com.isbn;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;


public class Book {
    private final Isbn isbn;
    private String title;
    private String author;

    public Book(Isbn isbn) throws IOException,IllegalArgumentException {
        this.isbn = isbn;
        if(!this.isbn.isValid())
            throw new IllegalArgumentException("Isbn is not valid");
        else
            fetchBookDetails();
    }

    private void fetchBookDetails() throws IOException {
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn.getFormattedIsbn();
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            
            parseBookDetails(response.toString());
        } else {
            throw new IOException("Failed to fetch book details. Response code: " + responseCode);
        }
    }

    private void parseBookDetails(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        if (json.has("items")) {
            JSONObject volumeInfo = json.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo");
            this.title = volumeInfo.optString("title", "Unknown Title");
            this.author = volumeInfo.optJSONArray("authors") != null ? volumeInfo.getJSONArray("authors").join(", ").replaceAll("\"", "") : "Unknown Author";
        } else {
            this.title = "Unknown Title";
            this.author = "Unknown Author";
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn.toString();
    }

    public String getFormattedIsbn() {
        return isbn.getFormattedIsbn();
    }

    @Override
    public String toString() {
        
        return  "Title: " + getTitle() + ", Author: " + getAuthor() + ", ISBN: " + getIsbn();
    }
}

