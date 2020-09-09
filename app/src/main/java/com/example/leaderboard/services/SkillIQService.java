package com.example.leaderboard.services;

import com.example.leaderboard.models.SkillIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIQService {

    @GET("skilliq")
    Call<List<SkillIQ>> getSkillIq();
}
