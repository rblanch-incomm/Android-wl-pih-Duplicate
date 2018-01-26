package com.incomm.payithere.models.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rblanch on 11/16/17.
 */

public class PaymentSlipDelivery {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("branded_for")
    @Expose
    private String brandedFor;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrandedFor() {
        return brandedFor;
    }

    public void setBrandedFor(String brandedFor) {
        this.brandedFor = brandedFor;
    }

}




