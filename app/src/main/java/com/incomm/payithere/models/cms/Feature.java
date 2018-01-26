package com.incomm.payithere.models.cms;

import android.os.Parcel;
import android.os.Parcelable;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("feature")
public class Feature extends Resource implements Parcelable {
    //public class Feature extends Resource {

    @Field
    String name;
    @Field
    String key;
    @Field
    String title;
    @Field
    Asset iconPrimary;
    @Field
    Asset iconSecondary;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getKey() {
        return ActivityUtils.checkNull(key);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public Asset getIconPrimary() {
        return iconPrimary;
    }

    public Asset getIconSecondary() {
        return iconSecondary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.key);
        dest.writeString(this.title);
        dest.writeParcelable(this.iconPrimary, flags);
        dest.writeParcelable(this.iconSecondary, flags);
    }

    public Feature() {
    }

    protected Feature(Parcel in) {
        this.name = in.readString();
        this.key = in.readString();
        this.title = in.readString();
        this.iconPrimary = in.readParcelable(Asset.class.getClassLoader());
        this.iconSecondary = in.readParcelable(Asset.class.getClassLoader());
    }

    public static final Parcelable.Creator<Feature> CREATOR = new Parcelable.Creator<Feature>() {
        @Override
        public Feature createFromParcel(Parcel source) {
            return new Feature(source);
        }

        @Override
        public Feature[] newArray(int size) {
            return new Feature[size];
        }
    };
}
