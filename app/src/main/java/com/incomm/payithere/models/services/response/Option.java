
package com.incomm.payithere.models.services.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Option implements Parcelable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("posting_time")
    @Expose
    private String postingTime;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("additional_info")
    @Expose
    private String additionalInfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(String postingTime) {
        this.postingTime = postingTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "Option{" +
                "token='" + token + '\'' +
                ", fee='" + fee + '\'' +
                ", postingTime='" + postingTime + '\'' +
                ", state='" + state + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.fee);
        dest.writeString(this.postingTime);
        dest.writeString(this.state);
        dest.writeString(this.additionalInfo);
    }

    public Option() {
    }

    protected Option(Parcel in) {
        this.token = in.readString();
        this.fee = in.readString();
        this.postingTime = in.readString();
        this.state = in.readString();
        this.additionalInfo = in.readString();
    }

    public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel source) {
            return new Option(source);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };
}
