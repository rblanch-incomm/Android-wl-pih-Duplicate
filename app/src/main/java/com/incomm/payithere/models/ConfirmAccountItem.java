package com.incomm.payithere.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by agodambe on 11/2/2017.
 */

public class ConfirmAccountItem implements Parcelable {

    private String itemLabel;
    private String itemValue;

    public ConfirmAccountItem(String itemLabel, String itemValue) {
        this.itemLabel = itemLabel;
        this.itemValue = itemValue;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemLabel);
        dest.writeString(this.itemValue);
    }

    public ConfirmAccountItem() {
    }

    protected ConfirmAccountItem(Parcel in) {
        this.itemLabel = in.readString();
        this.itemValue = in.readString();
    }

    public static final Parcelable.Creator<ConfirmAccountItem> CREATOR = new Parcelable.Creator<ConfirmAccountItem>() {
        @Override
        public ConfirmAccountItem createFromParcel(Parcel source) {
            return new ConfirmAccountItem(source);
        }

        @Override
        public ConfirmAccountItem[] newArray(int size) {
            return new ConfirmAccountItem[size];
        }
    };
}
