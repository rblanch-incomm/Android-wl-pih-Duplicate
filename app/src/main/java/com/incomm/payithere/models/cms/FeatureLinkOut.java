package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("featureLinkOut")
public class FeatureLinkOut extends Feature {

    @Field
    LinkOutView linkOutViewAndroid;
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

    public LinkOutView getLinkOutViewAndroid() {
        return linkOutViewAndroid;
    }
}
