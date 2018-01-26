package com.incomm.payithere.models.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class User {

    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;


    /*@SerializedName("current_password")
    @Expose
    private String current_pwd;

    @SerializedName("password")
    @Expose
    private String new_pwd;

    @SerializedName("password_confirmation")
    @Expose
    private String confirm_pwd;

    public void setCurrent_pwd(String current_pwd) {
        this.current_pwd = current_pwd;
    }

    public void setNew_pwd(String new_pwd) {
        this.new_pwd = new_pwd;
    }

    public void setConfirm_pwd(String confirm_pwd) {
        this.confirm_pwd = confirm_pwd;
    }*/

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}