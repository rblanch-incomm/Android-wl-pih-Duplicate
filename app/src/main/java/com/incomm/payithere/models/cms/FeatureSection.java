package com.incomm.payithere.models.cms;

import android.os.Parcel;
import android.os.Parcelable;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("featureSection")
public class FeatureSection extends Feature implements Parcelable {
    //public class FeatureSection extends Feature {
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
    @Field
    List<Feature> features;

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

    public List<Feature> getFeatures() {
        return features;
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
        dest.writeList(this.features);
    }

    public FeatureSection() {
    }

    protected FeatureSection(Parcel in) {
        this.name = in.readString();
        this.key = in.readString();
        this.title = in.readString();
        this.iconPrimary = in.readParcelable(Asset.class.getClassLoader());
        this.iconSecondary = in.readParcelable(Asset.class.getClassLoader());
        this.features = new ArrayList<Feature>();
        in.readList(this.features, Feature.class.getClassLoader());
    }

    public static final Creator<FeatureSection> CREATOR = new Creator<FeatureSection>() {
        @Override
        public FeatureSection createFromParcel(Parcel source) {
            return new FeatureSection(source);
        }

        @Override
        public FeatureSection[] newArray(int size) {
            return new FeatureSection[size];
        }
    };
}
