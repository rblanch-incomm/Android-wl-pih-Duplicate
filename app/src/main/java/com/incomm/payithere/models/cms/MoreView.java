package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("moreView")
public class MoreView extends View {
    @Field
    List<Feature> features;
    @Field
    String logoutButtonTitle;
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    String usernamePrefixLabel;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public String getUsernamePrefixLabel() {
        return usernamePrefixLabel;
    }

    public String getLogoutButtonTitle() {
        return ActivityUtils.checkNull(logoutButtonTitle);
    }
}
