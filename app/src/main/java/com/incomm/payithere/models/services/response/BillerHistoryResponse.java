
package com.incomm.payithere.models.services.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillerHistoryResponse {

    @SerializedName("biller_accounts")
    @Expose
    private List<BillerAccount> billerAccounts = null;

    public List<BillerAccount> getBillerAccounts() {
        return billerAccounts;
    }

    public void setBillerAccounts(List<BillerAccount> billerAccounts) {
        this.billerAccounts = billerAccounts;
    }

}
