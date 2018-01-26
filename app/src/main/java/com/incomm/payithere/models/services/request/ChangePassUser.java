package com.incomm.payithere.models.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class ChangePassUser {

    @SerializedName("current_password")
    @Expose
    private String current_pwd;
    @SerializedName("password")
    @Expose
    private String new_pwd;
    @SerializedName("password_confirmation")
    @Expose
    private String confirm_pwd;

    public ChangePassUser(String current_pwd, String new_pwd, String confirm_pwd) {
        this.current_pwd = current_pwd;
        this.new_pwd = new_pwd;
        this.confirm_pwd = confirm_pwd;
    }

    public void setCurrent_pwd(String current_pwd) {
        this.current_pwd = current_pwd;
    }

    public void setNew_pwd(String new_pwd) {
        this.new_pwd = new_pwd;
    }

    public void setConfirm_pwd(String confirm_pwd) {
        this.confirm_pwd = confirm_pwd;
    }
}