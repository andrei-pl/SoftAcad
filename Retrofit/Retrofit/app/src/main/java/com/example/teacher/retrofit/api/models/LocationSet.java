package com.example.teacher.retrofit.api.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Teacher on 1/19/2015.
 */
public class LocationSet {

    @Expose
    private LocationInfo results;
    @Expose
    private String status;

    /**
     *
     * @return
     * The results
     */
    public LocationInfo getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(LocationInfo results) {
        this.results = results;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
