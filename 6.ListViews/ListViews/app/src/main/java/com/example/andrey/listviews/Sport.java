package com.example.andrey.listviews;

/**
 * Created by Andrey on 4.12.2014 г..
 */
public class Sport {
    private int id;
    private String name;
    private int participants;

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
