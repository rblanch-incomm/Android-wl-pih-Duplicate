package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("accountDashboardView")
public class AccountDashboardView extends View {

    @Field
    List<Feature> features;
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
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
}
