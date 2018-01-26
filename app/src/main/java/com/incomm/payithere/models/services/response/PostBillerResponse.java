
package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostBillerResponse {

    @SerializedName("payment")
    @Expose
    private PaymentPost payment;

    public PaymentPost getPayment() {
        return payment;
    }

    public void setPayment(PaymentPost payment) {
        this.payment = payment;
    }

}
