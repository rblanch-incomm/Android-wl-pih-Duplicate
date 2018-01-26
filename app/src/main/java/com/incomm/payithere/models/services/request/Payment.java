package com.incomm.payithere.models.services.request;

import com.google.gson.JsonObject;

/**
 * Created by rblanch on 11/8/17.
 */

public class Payment {

    private JsonObject user_data;
    private String created_in;

    public JsonObject getUser_data() {
        return user_data;
    }

    public void setUser_data(JsonObject user_data) {
        this.user_data = user_data;
    }

    public String getCreated_in() {
        return created_in;
    }

    public void setCreated_in(String created_in) {
        this.created_in = created_in;
    }
}
