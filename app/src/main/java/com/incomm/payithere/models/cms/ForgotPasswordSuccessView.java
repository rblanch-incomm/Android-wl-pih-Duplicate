package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("forgotPasswordSuccessView")
public class ForgotPasswordSuccessView extends View {

    @Field
    String body;
    @Field
    String continueButtonTitle;
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    String AnalyticsId;

    public String getName() {
        return ActivityUtils.checkNull(name);
    }

    public String getTitle() {
        return ActivityUtils.checkNull(title);
    }

    public String getChannel() {
        return ActivityUtils.checkNull(channel);
    }

    public String getBody() {
        return ActivityUtils.checkNull(body);
    }

    public String getContinueButtonTitle() {
        return ActivityUtils.checkNull(continueButtonTitle);
    }

    public String getAnalyticsId() {
        return AnalyticsId;
    }
}
