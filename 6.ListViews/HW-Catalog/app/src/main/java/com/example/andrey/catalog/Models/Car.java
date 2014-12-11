package com.example.andrey.catalog.Models;

import android.graphics.Bitmap;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Andrey on 9.12.2014 г..
 */

//@ParseClassName("MyCars")
public class Car{
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
