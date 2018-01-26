package com.incomm.payithere.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by agodambe on 10/2/2017.
 */

public class BillsHistoryItem implements Parcelable {

    private String billerName;
    private String amount;
    private String status;
    private String text;
    private String textColor;
    private Date timestamp;
    private String token;
    private String billerIdToken;

    public String getBillerIdToken() {
        return billerIdToken;
    }

    public void setBillerIdToken(String billerIdToken) {
        this.billerIdToken = billerIdToken;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.billerName);
        dest.writeString(this.amount);
        dest.writeString(this.status);
        dest.writeString(this.text);
        dest.writeString(this.textColor);
        dest.writeLong(this.timestamp != null ? this.timestamp.getTime() : -1);
        dest.writeString(this.token);
        dest.writeString(this.billerIdToken);
    }

    public BillsHistoryItem() {
    }

    protected BillsHistoryItem(Parcel in) {
        this.billerName = in.readString();
        this.amount = in.readString();
        this.status = in.readString();
        this.text = in.readString();
        this.textColor = in.readString();
        long tmpTimestamp = in.readLong();
        this.timestamp = tmpTimestamp == -1 ? null : new Date(tmpTimestamp);
        this.token = in.readString();
        this.billerIdToken = in.readString();
    }

    public static final Parcelable.Creator<BillsHistoryItem> CREATOR = new Parcelable.Creator<BillsHistoryItem>() {
        @Override
        public BillsHistoryItem createFromParcel(Parcel source) {
            return new BillsHistoryItem(source);
        }

        @Override
        public BillsHistoryItem[] newArray(int size) {
            return new BillsHistoryItem[size];
        }
    };

    @Override
    public String toString() {
        return "BillsHistoryItem{" +
                "billerName='" + billerName + '\'' +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                ", text='" + text + '\'' +
                ", textColor='" + textColor + '\'' +
                ", timestamp=" + timestamp +
                ", token=" + token + '\'' +
                ", billerIdToken='" + billerIdToken + '\'' +
                '}';
    }
}
