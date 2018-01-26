
package com.incomm.payithere.models.services.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("biller_groups")
    @Expose
    private List<BillerGroup> billerGroups = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BillerGroup> getBillerGroups() {
        return billerGroups;
    }

    public void setBillerGroups(List<BillerGroup> billerGroups) {
        this.billerGroups = billerGroups;
    }

}
