package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rblanch on 11/10/17.
 */

public class Attributes {

    @SerializedName("user_data")
    @Expose
    private List<String> userData = null;

    public List<String> getUserData() {
        return userData;
    }

    public void setUserData(List<String> userData) {
        this.userData = userData;
    }

}
