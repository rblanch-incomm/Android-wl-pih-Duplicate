package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("aboutView")
public class AboutView extends View {

    @Field
    Asset logo;
    @Field
    String body;
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    FeatureContextualHelp contextHelpTest;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }

    public FeatureContextualHelp getContextHelpTest() {
        return contextHelpTest;
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

    public Asset getLogo() {
        return logo;
    }

    public String getBody() {
        return ActivityUtils.checkNull(body);
    }

    public FeatureContextualHelp getFeatureContextualHelp() {
        return contextHelpTest;
    }
}
