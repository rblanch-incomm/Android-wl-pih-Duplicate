package com.incomm.payithere.models.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rblanch on 11/16/17.
 */

public class EmailPaymentSlipRequest {

    @SerializedName("payment_slip_delivery")
    @Expose
    private PaymentSlipDelivery paymentSlipDelivery;

    public PaymentSlipDelivery getPaymentSlipDelivery() {
        return paymentSlipDelivery;
    }

    public void setPaymentSlipDelivery(PaymentSlipDelivery paymentSlipDelivery) {
        this.paymentSlipDelivery = paymentSlipDelivery;
    }

}
