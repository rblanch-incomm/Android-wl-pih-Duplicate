
package com.incomm.payithere.models.services.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillerAccount {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("account_number")
    @Expose
    private String accountNumber;
    @SerializedName("user_data")
    @Expose
    private UserData userData;
    @SerializedName("biller")
    @Expose
    private Biller biller;
    @SerializedName("payments")
    @Expose
    private List<Payment> payments = null;
    @SerializedName("ask_user_for_amount")
    @Expose
    private Boolean askUserForAmount;
    @SerializedName("reminder")
    @Expose
    private Reminder reminder;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Biller getBiller() {
        return biller;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Boolean getAskUserForAmount() {
        return askUserForAmount;
    }

    public void setAskUserForAmount(Boolean askUserForAmount) {
        this.askUserForAmount = askUserForAmount;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

}
