package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bjennings on 10/16/2017.
 */

public class PaymentWithEreceiptResponse {
    @SerializedName("payment_with_ereceipt")
    @Expose
    private PaymentWithEreceipt payment = null;

    public PaymentWithEreceipt getPayment() { return payment; }

    public void setPayment(PaymentWithEreceipt payment) {
        this.payment = payment;
    }
}
