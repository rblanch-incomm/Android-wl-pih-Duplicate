package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("forgotCredentialsView")
public class ForgotCredentialsView extends View {
    @Field
    String forgotPasswordButtonTitle;
    @Field
    String forgotUsernameButtonTitle;
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

    public String getForgotPasswordButtonTitle() {
        return ActivityUtils.checkNull(forgotPasswordButtonTitle);
    }

    public String getForgotUsernameButtonTitle() {
        return ActivityUtils.checkNull(forgotUsernameButtonTitle);
    }
}
