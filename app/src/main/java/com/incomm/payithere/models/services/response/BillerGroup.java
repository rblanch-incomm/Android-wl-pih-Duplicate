
package com.incomm.payithere.models.services.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillerGroup implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("logo_url")
    @Expose
    private Object logoUrl;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Object logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.name);
        dest.writeList(this.options);
    }

    @Override
    public int describeContents() {
        return 0;
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
