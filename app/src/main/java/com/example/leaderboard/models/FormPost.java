package com.example.leaderboard.models;

import com.google.gson.annotations.SerializedName;

public class FormPost {
    private String firstName;
    private String emailAddress;
    private String lastName;
    private String projectLink;

    public FormPost(String firstName, String emailAddress, String lastName, String projectLink) {
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.lastName = lastName;
        this.projectLink = projectLink;
    }

    public String getName() {
        return firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLinkProject() {
        return projectLink;
    }
}
