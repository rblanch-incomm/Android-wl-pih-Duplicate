
package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillerByNameResponse {

    @SerializedName("biller_groups")
    @Expose
    private List<BillerGroup> billerGroups = null;

    public List<BillerGroup> getBillerGroups() {
        return billerGroups;
    }

    public void setBillerGroups(List<BillerGroup> billerGroups) {
        this.billerGroups = billerGroups;
    }

}
