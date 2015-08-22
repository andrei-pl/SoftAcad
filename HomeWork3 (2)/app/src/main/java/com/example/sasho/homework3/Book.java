package com.example.sasho.homework3;

/**
 * Created by Sasho on 9.12.2014 г..
 */
public class Book {
    private String  Title;
    private String  Publisher;
    private String  Writer;
    private String  ISBN;
    private int     Year;
    private String  Language;
    private String  Description;

    public Book() {
    }

    public Book(String title, String publisher, String writer, String ISBN, int year, String language, String description) {
        Title = title;
        Publisher = publisher;
        Writer = writer;
        this.ISBN = ISBN;
        Year = year;
        Language = language;
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
