package com.incomm.payithere.models.services.request;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by agodambe on 11/4/2017.
 */

public class ConfirmPaymentRequest {
    @SerializedName("payment")
    @Expose
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
