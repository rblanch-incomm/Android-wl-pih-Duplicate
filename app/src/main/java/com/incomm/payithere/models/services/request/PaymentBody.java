package com.incomm.payithere.models.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by agodambe on 11/6/2017.
 */

public class PaymentBody {
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getAmount() {
        return amount;
    }

    public PaymentBody(String amount) {
        this.amount = amount;
    }
}
