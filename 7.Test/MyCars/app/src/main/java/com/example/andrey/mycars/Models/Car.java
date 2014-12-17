package com.example.andrey.mycars.Models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Andrey on 12/15/2014.
 */
public class Car implements Serializable{
    private String objectId;
    private String model;
    private String year;
    private String condition;
    private Bitmap image;

    public Car(String model, String year, String condition) {
        this.model = model;
        this.year = year;
        this.condition = condition;
        //this.image = image;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
