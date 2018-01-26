package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rrblanch-incomm on 11/6/17.
 */

public class EnterAccountInformationResponse {
    @SerializedName("biller")
    @Expose
    private SpecificBillerInformation biller;

    public SpecificBillerInformation getBiller() {
        return biller;
    }

    public void setBiller(SpecificBillerInformation biller) {
        this.biller = biller;
    }
}
