package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rblanch on 11/8/17.
 */

public class PaymentPostResponse {
    @SerializedName("payment")
    @Expose
    protected PaymentPost paymentPost;

    public PaymentPost getPaymentPost() {
        return paymentPost;
    }

    public void setPaymentPost(PaymentPost paymentPost) {
        this.paymentPost = paymentPost;
    }
}
