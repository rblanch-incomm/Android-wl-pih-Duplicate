package com.incomm.payithere.models.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by agodambe on 9/13/2017.
 */

public class SignUpUser {

    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("password_confirmation")
    @Expose
    private String passwordConfirmation;

    public SignUpUser(String firstName, String lastName, String email, String phone, String password, String passwordConfirmation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

}
