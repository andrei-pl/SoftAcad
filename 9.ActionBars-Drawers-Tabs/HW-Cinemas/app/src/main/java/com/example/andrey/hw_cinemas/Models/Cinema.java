package com.example.andrey.hw_cinemas.Models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrey on 31.12.2014 г..
 */
public class Cinema {
    private ArrayList<Movie> movies;
    private String name;
    private Bitmap picCinema1;
    private Bitmap picCinema2;
    private String address;
    private ArrayList<String> workTimes;
    private HashMap<Movie, Integer> moviesSeats;

    public HashMap<Movie, Integer> getMoviesSeats() {
        return moviesSeats;
    }

    public void setMoviesSeats(HashMap<Movie, Integer> moviesSeats) {
        this.moviesSeats = moviesSeats;
    }

    public void setMoviesSeats(Movie movie, Integer seats) {
        this.moviesSeats.put(movie, seats);
    }

    public Bitmap getPicCinema1() {
        return picCinema1;
    }

    public void setPicCinema1(Bitmap picCinema1) {
        this.picCinema1 = picCinema1;
    }

    public Bitmap getPicCinema2() {
        return picCinema2;
    }

    public void setPicCinema2(Bitmap picCinema2) {
        this.picCinema2 = picCinema2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(ArrayList<String> workTimes) {
        this.workTimes = workTimes;
    }

    public Cinema(ArrayList<String> workTimes, String address, Bitmap picCinema2, Bitmap picCinema1, String name) {
        this.movies = new ArrayList<Movie>();
        this.workTimes = workTimes;
        this.address = address;
        this.picCinema2 = picCinema2;
        this.picCinema1 = picCinema1;
        this.name = name;
        this.moviesSeats = new HashMap<Movie, Integer>();
    }


    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) { this.movies = movies; }

    public void setMovies(Movie movie, Integer seats) {

        this.movies.add(movie);
        this.setMoviesSeats(movie, seats);
        movie.setCinemas(this, seats);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
