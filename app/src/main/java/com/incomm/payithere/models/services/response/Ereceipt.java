package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bjennings on 10/16/2017.
 * More fields exist in the response that generates this.
 */

public class Ereceipt {
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("confirmation_number")
    @Expose
    private Integer confirmationNumber;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("convenience_fee")
    @Expose
    private String fee;
    @SerializedName("total_amount")
    @Expose
    private String total;
    @SerializedName("hints")
    @Expose
    private List<String> hints = null;

    public Integer getConfirmationNumber() {
        return confirmationNumber;
    }

    public List<String> getHints() {
        return hints;
    }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getAmount() { return amount; }

    public void setAmount(String amount) { this.amount = amount; }

    public String getFee() { return fee; }

    public void setFee(String fee) { this.fee = fee; }

    public String getTotal() { return total; }

    public void setTotal(String total) { this.total = total; }
}
