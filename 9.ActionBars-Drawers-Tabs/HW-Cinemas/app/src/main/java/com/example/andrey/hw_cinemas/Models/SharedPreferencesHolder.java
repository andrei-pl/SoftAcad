package com.example.andrey.hw_cinemas.Models;

/**
 * Created by Andrey on 30.12.2014 г..
 */
public class SharedPreferencesHolder {
    private static SharedPreferencesHolder sharedPreferencesHolder = null;
    public String user;
    public String pass;

    public static SharedPreferencesHolder getInstance(){
        if(sharedPreferencesHolder == null){
            sharedPreferencesHolder = new SharedPreferencesHolder();
        }

        return sharedPreferencesHolder;
    }

    private SharedPreferencesHolder() {
        user = null;
        pass = null;
    }
}
