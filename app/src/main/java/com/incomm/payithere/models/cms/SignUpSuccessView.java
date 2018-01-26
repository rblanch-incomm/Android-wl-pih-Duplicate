package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Aashish Godambe on 9/14/17.
 */
@ContentType("signUpSuccess")
public class SignUpSuccessView extends View {
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    String body;
    @Field
    String continueButtonTitle;
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

    public String getBody() {
        return ActivityUtils.checkNull(body);
    }

    public String getContinueButtonTitle() {
        return ActivityUtils.checkNull(continueButtonTitle);
    }
}
