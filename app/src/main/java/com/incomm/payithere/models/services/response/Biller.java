
package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Biller {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("posting_time")
    @Expose
    private String postingTime;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(String postingTime) {
        this.postingTime = postingTime;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
