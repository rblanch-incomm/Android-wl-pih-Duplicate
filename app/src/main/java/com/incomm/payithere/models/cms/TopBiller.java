package com.incomm.payithere.models.cms;

import android.os.Parcel;
import android.os.Parcelable;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Aashish Godambe on 3/1/17.
 */
@ContentType("topBiller")
public class TopBiller extends Resource{
    //public class Feature extends Resource {

    @Field
    String name;
    @Field
    String token;
    @Field
    Asset logoImage;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getToken() {
        return ActivityUtils.checkNull(token);
    }

    public Asset getLogoImage() {
        return logoImage;
    }
}
