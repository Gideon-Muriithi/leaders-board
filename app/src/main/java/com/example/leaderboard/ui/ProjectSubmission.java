package com.example.leaderboard.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.leaderboard.R;
import com.example.leaderboard.services.GoogleFormSubmission;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProjectSubmission extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.google_form_button)
    Button mGoogleFormSubmitButton;
    @BindView(R.id.project_submit_toolbar)
    Toolbar toolbar;

    final static Retrofit RETROFIT = new Retrofit.Builder().baseUrl
            ("https://docs.google.com/forms/d/e/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    @BindView((R.id.first_name)) EditText mFirstName;
    @BindView(R.id.last_name) EditText mLastName;
    @BindView(R.id.email_address) EditText mEmailAddress;
    @BindView(R.id.git_hub_link) EditText mProjectLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        ButterKnife.bind(this);

        mGoogleFormSubmitButton.setOnClickListener(this);


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public void onClick(View view) {
        if (view == mGoogleFormSubmitButton) {
            String firstName = mFirstName.getText().toString();
            String lastName = mLastName.getText().toString();
            String emailAddress = mEmailAddress.getText().toString();
            String gitHubLink = mProjectLink.getText().toString();

            executeSendGoogleForm(firstName, emailAddress, lastName, gitHubLink);

            mFirstName.getText().clear();
            mLastName.getText().clear();
            mEmailAddress.getText().clear();
            mProjectLink.getText().clear();
            Log.d("Test", mLastName.getText().toString());

        }
    }


    private void executeSendGoogleForm(String firstName, String emailAddress, String lastName, String projectLink) {
        GoogleFormSubmission formPost = RETROFIT.create(GoogleFormSubmission.class);

        Call<ResponseBody> call = formPost.submitProject(firstName, emailAddress, lastName, projectLink);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    FragmentManager fm = getSupportFragmentManager();
                    SuccessfulSubmissionDialog successfulSubmission = new SuccessfulSubmissionDialog();
                    successfulSubmission.show(fm, "");
                } else {
                    Toast.makeText(ProjectSubmission.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                FragmentManager fm = getSupportFragmentManager();
                FailedSubmissionDialog failedSubmission = new FailedSubmissionDialog();
                failedSubmission.show(fm, "");
            }
        });

    }

}