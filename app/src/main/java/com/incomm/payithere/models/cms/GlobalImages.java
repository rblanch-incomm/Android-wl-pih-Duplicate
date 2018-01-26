package com.incomm.payithere.models.cms;

import android.os.Parcel;
import android.os.Parcelable;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;

/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("globalImages")
public class GlobalImages extends Resource implements Parcelable {

    @Field
    String name;
    @Field
    Asset splashImage;
    @Field
    Asset titleLogo;
    @Field
    Asset navigationBar;
    @Field
    Asset background;
    @Field
    Asset backButton;
    @Field
    Asset chevronRight;
    @Field
    Asset chevronRightSecondary;
    @Field
    Asset callButton;
    @Field
    Asset directionsButton;

    public String getName() {
        return name;
    }

    public Asset getSplashImage() {
        return splashImage;
    }

    public Asset getTitleLogo() {
        return titleLogo;
    }

    public Asset getNavigationBar() {
        return navigationBar;
    }

    public Asset getBackground() {
        return background;
    }

    public void setBackground(Asset background) {
        this.background = background;
    }

    public Asset getBackButton() {
        return backButton;
    }

    public Asset getChevronRight() {
        return chevronRight;
    }

    public Asset getChevronRightSecondary() {
        return chevronRightSecondary;
    }

    public Asset getCallButton() {
        return callButton;
    }

    public Asset getDirectionsButton() {
        return directionsButton;
    }

    public GlobalImages() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.splashImage, flags);
        dest.writeParcelable(this.titleLogo, flags);
        dest.writeParcelable(this.navigationBar, flags);
        dest.writeParcelable(this.background, flags);
        dest.writeParcelable(this.backButton, flags);
        dest.writeParcelable(this.chevronRight, flags);
        dest.writeParcelable(this.chevronRightSecondary, flags);
        dest.writeParcelable(this.callButton, flags);
        dest.writeParcelable(this.directionsButton, flags);
    }

    protected GlobalImages(Parcel in) {
        this.name = in.readString();
        this.splashImage = in.readParcelable(Asset.class.getClassLoader());
        this.titleLogo = in.readParcelable(Asset.class.getClassLoader());
        this.navigationBar = in.readParcelable(Asset.class.getClassLoader());
        this.background = in.readParcelable(Asset.class.getClassLoader());
        this.backButton = in.readParcelable(Asset.class.getClassLoader());
        this.chevronRight = in.readParcelable(Asset.class.getClassLoader());
        this.chevronRightSecondary = in.readParcelable(Asset.class.getClassLoader());
        this.callButton = in.readParcelable(Asset.class.getClassLoader());
        this.directionsButton = in.readParcelable(Asset.class.getClassLoader());
    }

    public static final Creator<GlobalImages> CREATOR = new Creator<GlobalImages>() {
        @Override
        public GlobalImages createFromParcel(Parcel source) {
            return new GlobalImages(source);
        }

        @Override
        public GlobalImages[] newArray(int size) {
            return new GlobalImages[size];
        }
    };
}
