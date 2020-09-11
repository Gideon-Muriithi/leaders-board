package com.example.leaderboard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.leaderboard.R;
import com.example.leaderboard.adapters.LearningLeaders;
import com.example.leaderboard.adapters.SkillIqLeaders;
import com.example.leaderboard.models.Hours;
import com.example.leaderboard.models.SkillIQ;
import com.example.leaderboard.services.LearningLeadersService;
import com.example.leaderboard.services.SkillIQService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class learning_skill_IQ_leaders extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.learning_leaders)
    TextView mLearningLeaders;
    @BindView(R.id.skill_iq_leaders)
    TextView mSkillIq;
    @BindView(R.id.submit_project_button)
    Button mButton;

    final static Retrofit RETROFIT = new Retrofit.Builder().baseUrl("https://gadsapi.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_skill__i_q_leaders);

        ButterKnife.bind(this);

        mLearningLeaders.setOnClickListener(this);
        mSkillIq.setOnClickListener(this);
        onClick(mLearningLeaders);
        mButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == mLearningLeaders) {
            LearningLeadersService learningLeaders = RETROFIT.create(LearningLeadersService.class);
            Call<List<Hours>> call = learningLeaders.getHours();

            call.enqueue(new Callback<List<Hours>>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NotNull Call<List<Hours>> call, @NotNull Response<List<Hours>> response) {

                    if (response.isSuccessful()) {
                        List<Hours> hours = response.body();

                        LearningLeaders adapter = new LearningLeaders(getApplicationContext(), hours);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(learning_skill_IQ_leaders.this));
                        mRecyclerView.setHasFixedSize(true);
                    } else {
                        mLearningLeaders.setText("Code: " + response.code());
                    }

                }

                @Override
                public void onFailure(@NotNull Call<List<Hours>> call, @NotNull Throwable t) {
                    mLearningLeaders.setText(t.getMessage());

                }
            });
        }

        if (view == mSkillIq) {
            SkillIQService skillLeaders = RETROFIT.create(SkillIQService.class);
            Call<List<SkillIQ>> call = skillLeaders.getSkillIq();

            call.enqueue(new Callback<List<SkillIQ>>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {
                    if (response.isSuccessful()) {
                        List<SkillIQ> skills = response.body();

                        SkillIqLeaders adapter = new SkillIqLeaders(getApplicationContext(), skills);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(learning_skill_IQ_leaders.this));
                        mRecyclerView.setHasFixedSize(true);
                    } else {
                        mLearningLeaders.setText("Code: " + response.code());
                    }

                }

                @Override
                public void onFailure(Call<List<SkillIQ>> call, Throwable t) {
                    mLearningLeaders.setText(t.getMessage());
                }
            });
        }

        if (view == mButton) {
            Intent intent = new Intent(this, ProjectSubmission.class);
            startActivity(intent);
        }

    }
}