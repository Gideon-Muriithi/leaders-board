package com.example.leaderboard.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import com.example.leaderboard.R;

import butterknife.BindView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuccessfulSubmissionDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.successful_dialog, container, false);

    }
}
