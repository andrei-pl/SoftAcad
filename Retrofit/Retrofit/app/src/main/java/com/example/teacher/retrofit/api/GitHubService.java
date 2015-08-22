package com.example.teacher.retrofit.api;

import com.example.teacher.retrofit.api.models.LocationSet;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Teacher on 1/19/2015.
 */
public interface GitHubService {
    @GET("/json?lat=36.7201600&lng=-4.4203400")
    void listRepos(Callback<LocationSet> cb);
}
