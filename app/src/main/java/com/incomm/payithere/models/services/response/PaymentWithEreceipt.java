package com.incomm.payithere.models.services.response;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by bjennings on 10/16/2017.
 * More fields exist in the call for this, I just added the ones i needed.
 */

public class PaymentWithEreceipt {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("accounts")
    @Expose
    private List<Object> accounts = null;

    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("next_step")
    @Expose
    private String nextStep;
    @SerializedName("biller")
    @Expose
    private Biller biller;
    @SerializedName("min_transaction_amount")
    @Expose
    private String minTransactionAmount;
    @SerializedName("max_transaction_amount")
    @Expose
    private String maxTransactionAmount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("reminder")
    @Expose
    private Object reminder;
    @SerializedName("ereceipt")
    @Expose
    private Ereceipt ereceipt;
    @SerializedName("authorized_agent_address")
    @Expose
    private String authorizedAgentAddress;
    @SerializedName("authorized_agent")
    @Expose
    private String authorizedAgent;
    @SerializedName("user_data")
    @Expose
    private Map<String, String> userData;

    public String getToken() {
        return token;
    }

    public List<Object> getAccounts() {
        return accounts;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getAmount() {
        return amount;
    }

    public String getFee() {
        return fee;
    }

    public String getStatus() {
        return status;
    }

    public String getNextStep() {
        return nextStep;
    }

    public Biller getBiller() {
        return biller;
    }

    public String getMinTransactionAmount() {
        return minTransactionAmount;
    }

    public String getMaxTransactionAmount() {
        return maxTransactionAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Object getReminder() {
        return reminder;
    }

    public Map<String, String> getUserData() { return userData; }

    public void setUserData(Map<String, String> userData) { this.userData = userData; }

    public Ereceipt getEreceipt() { return ereceipt; }

    public void setEreceipt(Ereceipt ereceipt) { this.ereceipt = ereceipt; }

    public String getAuthorizedAgent() { return authorizedAgent; }

    public void setAuthorizedAgent(String authorizedAgent) { this.authorizedAgent = authorizedAgent; }

    public String getAuthorizedAgentAddress() { return this.authorizedAgentAddress; }

    /*public String getFormattedAuthorizedAgentAddress() {
        String[] splitAddress = this.authorizedAgentAddress.split(",");
        String[] splitStateZip = splitAddress[2].trim().split(" ");
        return String.format("%s%n%s, %s, %s%n%s", splitAddress[0].trim(), splitAddress[1].trim(), splitStateZip[0].trim(), splitAddress[3].trim(), splitStateZip[1].trim());
    }*/

    public String getFormattedAuthorizedAgentAddress() {
        try {
            String[] splitAddress = this.authorizedAgentAddress.split(",");
            String phone1 = splitAddress[3].substring(0, 4).trim();
            String phone2 = splitAddress[3].substring(4, 7).trim();
            String phone3 = splitAddress[3].substring(7, splitAddress[3].length()).trim();
            //Log.e("TAG", splitAddress[0] + " 0 " + splitAddress[1]+ " 1 " + splitAddress[2] + splitAddress[4] + " 2 " + phone1 + "-" + phone2 + "-" + phone3);
            return String.format("%s%n%s, %s, %s%n%s", splitAddress[0].trim(), splitAddress[1].trim(), splitAddress[2].trim(), splitAddress[4].trim(), phone1 + "-" + phone2 + "-" + phone3);
        } catch (Exception e) {
            return "Address not available at this time.";
        }
    }

    public void setAuthorizedAgentAddress(String authorizedAgentAddress) { this.authorizedAgentAddress = authorizedAgentAddress; }
}
