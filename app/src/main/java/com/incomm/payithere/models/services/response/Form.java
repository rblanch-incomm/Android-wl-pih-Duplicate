
package com.incomm.payithere.models.services.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Form implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("max_len")
    @Expose
    private String maxLen;
    @SerializedName("min_len")
    @Expose
    private String minLen;
    @SerializedName("required")
    @Expose
    private String required;
    @SerializedName("requires_confirmation")
    @Expose
    private String requiresConfirmation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(String maxLen) {
        this.maxLen = maxLen;
    }

    public String getMinLen() {
        return minLen;
    }

    public void setMinLen(String minLen) {
        this.minLen = minLen;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getRequiresConfirmation() {
        return requiresConfirmation;
    }

    public void setRequiresConfirmation(String requiresConfirmation) {
        this.requiresConfirmation = requiresConfirmation;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.label);
        dest.writeString(this.maxLen);
        dest.writeString(this.minLen);
        dest.writeString(this.required);
        dest.writeString(this.requiresConfirmation);
    }

    public Form() {
    }

    protected Form(Parcel in) {
        this.name = in.readString();
        this.type = in.readString();
        this.label = in.readString();
        this.maxLen = in.readString();
        this.minLen = in.readString();
        this.required = in.readString();
        this.requiresConfirmation = in.readString();
    }

    public static final Parcelable.Creator<Form> CREATOR = new Parcelable.Creator<Form>() {
        @Override
        public Form createFromParcel(Parcel source) {
            return new Form(source);
        }

        @Override
        public Form[] newArray(int size) {
            return new Form[size];
        }
    };
}
