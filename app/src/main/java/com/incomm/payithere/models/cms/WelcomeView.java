package com.incomm.payithere.models.cms;

/**
 * Created by Jerome Davis on 3/15/17.
 */

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

@ContentType("welcomeView")
public class WelcomeView extends View {
    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    Asset promotionalImage;
    @Field
    String signInButtonTitle;
    @Field
    List<Feature> features;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    @Override
    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    @Override
    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    @Override
    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public Asset getPromotionalImage() {
        return promotionalImage;
    }

    public String getSignInButtonTitle() {
        return ActivityUtils.checkNull(signInButtonTitle);
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
