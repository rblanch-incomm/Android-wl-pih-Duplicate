package com.incomm.payithere.models.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class SignUpRequest {
    @SerializedName("user")
    @Expose
    private SignUpUser signUpUser;
    public SignUpUser getUser() {
        return signUpUser;
    }

    public void setUser(SignUpUser signUpUser) {
        this.signUpUser = signUpUser;
    }
}