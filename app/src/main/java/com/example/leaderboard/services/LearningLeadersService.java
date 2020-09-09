package com.example.leaderboard.services;

import com.example.leaderboard.models.Hours;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningLeadersService {

    @GET("hours")
    Call<List<Hours>> getHours();
}
