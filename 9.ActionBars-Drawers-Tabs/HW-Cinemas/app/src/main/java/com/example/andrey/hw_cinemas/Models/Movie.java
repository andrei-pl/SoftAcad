package com.example.andrey.hw_cinemas.Models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Andrey on 31.12.2014 г..
 */
public class Movie {
    private String name;
    private ArrayList<String> casts;
    private Bitmap picMovie;
    private HashMap<Cinema, Integer> cinemas;

    public Movie(String name, Bitmap picMovie, ArrayList<String> casts) {
        this.name = name;
        this.cinemas = new HashMap<Cinema, Integer>();
        this.picMovie = picMovie;
        this.casts = casts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Cinema, Integer> getCinemas() {
        return cinemas;
    }

    public void setCinemas(HashMap<Cinema, Integer> cinemas) {
        this.cinemas = cinemas;
    }

    public void setCinemas(Cinema cinema, Integer seats) {
        this.cinemas.put(cinema, seats);
    }

    public Bitmap getPicMovie() {
        return picMovie;
    }

    public void setPicMovie(Bitmap picMovie) {
        this.picMovie = picMovie;
    }

    public ArrayList<String> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<String> casts) {
        this.casts = casts;
    }
}
