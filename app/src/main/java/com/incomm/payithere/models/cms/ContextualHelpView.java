package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

/**
 * Created by Jerome Davis on 9/11/17.
 */
@ContentType("contextualHelpView")
public class ContextualHelpView extends View {

    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    String bodyText;
    @Field
    String dismissButtonText;
    @Field
    Asset helpImage;
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
    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    @Override
    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public String getBodyText() {
        return ActivityUtils.checkNull(bodyText);
    }

    public String getDismissButtonText() {
        return ActivityUtils.checkNull(dismissButtonText);
    }

    public Asset getHelpImage() {
        return helpImage;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public void setHelpImage(Asset helpImage) {
        this.helpImage = helpImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
