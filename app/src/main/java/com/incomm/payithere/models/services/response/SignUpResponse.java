package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by agodambe on 9/14/2017.
 */

public class SignUpResponse {
    @SerializedName("user")
    @Expose
    private User user;
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }
}
