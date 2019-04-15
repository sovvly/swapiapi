package com.example.swapiapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SwapiClient {

    @GET("/people/")
    Call<List<StarWarsPeople>> reposForActors();

}
