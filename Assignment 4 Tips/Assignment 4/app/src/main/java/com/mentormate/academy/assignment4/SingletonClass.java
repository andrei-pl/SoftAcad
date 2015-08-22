package com.mentormate.academy.assignment4;

/**
 * Created by Stefan.Doychev on 26.11.2014.
 */
public class SingletonClass {

    private static int userId = 0;
    private static SingletonClass instance = null;

    protected SingletonClass() {
        // Exists only to defeat instantiation.
    }

    public static SingletonClass getInstance() {
        if(instance == null) {
            instance = new SingletonClass();
        }
        return instance;
    }

    public int getUserId() {

        return ++userId;
    }
}