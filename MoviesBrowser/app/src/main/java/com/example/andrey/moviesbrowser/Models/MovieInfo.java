package com.example.andrey.moviesbrowser.Models;

/**
 * Created by Andrey on 29.1.2015 г..
 */
public class MovieInfo {
    private String id;
    private String title;
    private String year;
    private String rating;
    private String runtime;
    private String imageUrl;

    public MovieInfo(String id, String title, String year, String rating, String runtime, String imageUrl) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.runtime = runtime;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
