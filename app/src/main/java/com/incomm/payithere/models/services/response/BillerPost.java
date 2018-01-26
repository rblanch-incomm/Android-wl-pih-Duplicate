
package com.incomm.payithere.models.services.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillerPost implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("external_id")
    @Expose
    private String externalId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("posting_time")
    @Expose
    private String postingTime;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("logo_url")
    @Expose
    private String logoUrl;
    @SerializedName("min_transaction_amount")
    @Expose
    private String minTransactionAmount;
    @SerializedName("max_transaction_amount")
    @Expose
    private String maxTransactionAmount;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("featured")
    @Expose
    private Object featured;
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
    @SerializedName("can_be_paid")
    @Expose
    private Object canBePaid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public Object getFeatured() {
        return featured;
    }

    public void setFeatured(Object featured) {
        this.featured = featured;
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

    public Object getCanBePaid() {
        return canBePaid;
    }

    public void setCanBePaid(Object canBePaid) {
        this.canBePaid = canBePaid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.externalId);
        dest.writeString(this.name);
        dest.writeString(this.postingTime);
        dest.writeString(this.fee);
        dest.writeString(this.logoUrl);
        dest.writeString(this.minTransactionAmount);
        dest.writeString(this.maxTransactionAmount);
        dest.writeString(this.category);
        dest.writeList(this.form);
        dest.writeValue(this.stripSpaces);
        dest.writeValue(this.stripDashes);
        dest.writeValue(this.anFormat);
    }

    public BillerPost() {
    }

    protected BillerPost(Parcel in) {
        this.id = in.readString();
        this.externalId = in.readString();
        this.name = in.readString();
        this.postingTime = in.readString();
        this.fee = in.readString();
        this.logoUrl = in.readString();
        this.minTransactionAmount = in.readString();
        this.maxTransactionAmount = in.readString();
        this.category = in.readString();
        this.form = new ArrayList<Form>();
        in.readList(this.form, Form.class.getClassLoader());
        this.stripSpaces = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.stripDashes = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.anFormat = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<BillerPost> CREATOR = new Parcelable.Creator<BillerPost>() {
        @Override
        public BillerPost createFromParcel(Parcel source) {
            return new BillerPost(source);
        }

        @Override
        public BillerPost[] newArray(int size) {
            return new BillerPost[size];
        }
    };
}
