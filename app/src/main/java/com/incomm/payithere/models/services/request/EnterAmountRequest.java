package com.incomm.payithere.models.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by agodambe on 11/6/2017.
 */

public class EnterAmountRequest {
    @SerializedName("payment")
    @Expose
    private PaymentBody payment;

    public PaymentBody getPayment() {
        return payment;
    }

    public void setPayment(PaymentBody payment) {
        this.payment = payment;
    }
}
