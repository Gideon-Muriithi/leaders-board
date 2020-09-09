package com.example.leaderboard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.leaderboard.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.launcher)
    ConstraintLayout launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        launcher.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == launcher) {
            Intent intent = new Intent(this, learning_skill_IQ_leaders.class);
            startActivity(intent);
        }
    }
}