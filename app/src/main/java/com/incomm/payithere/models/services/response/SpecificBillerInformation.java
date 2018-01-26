package com.incomm.payithere.models.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rblanch on 11/6/17.
 */

public class SpecificBillerInformation {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("posting_time")
    @Expose
    private String postingTime;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("min_transaction_amount")
    @Expose
    private String minTransactionAmount;
    @SerializedName("max_transaction_amount")
    @Expose
    private String maxTransactionAmount;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("form")
    @Expose
    private List<Form> form = null;
    @SerializedName("strip_spaces")
    @Expose
    private Boolean stripSpaces;
    @SerializedName("strip_dashes")
    @Expose
    private Boolean stripDashes;
    @SerializedName("an_format")
    @Expose
    private Integer anFormat;

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

    public String getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(String postingTime) {
        this.postingTime = postingTime;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getMinTransactionAmount() {
        return minTransactionAmount;
    }

    public void setMinTransactionAmount(String minTransactionAmount) {
        this.minTransactionAmount = minTransactionAmount;
    }

    public String getMaxTransactionAmount() {
        return maxTransactionAmount;
    }

    public void setMaxTransactionAmount(String maxTransactionAmount) {
        this.maxTransactionAmount = maxTransactionAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Form> getForm() {
        return form;
    }

    public void setForm(List<Form> form) {
        this.form = form;
    }

    public Boolean getStripSpaces() {
        return stripSpaces;
    }

    public void setStripSpaces(Boolean stripSpaces) {
        this.stripSpaces = stripSpaces;
    }

    public Boolean getStripDashes() {
        return stripDashes;
    }

    public void setStripDashes(Boolean stripDashes) {
        this.stripDashes = stripDashes;
    }

    public Integer getAnFormat() {
        return anFormat;
    }

    public void setAnFormat(Integer anFormat) {
        this.anFormat = anFormat;
    }
}
