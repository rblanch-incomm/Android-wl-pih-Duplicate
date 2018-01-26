package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("forgotUsernameView")
public class ForgotUsernameView extends View {
    @Field
    String body;
    @Field
    String supportPhoneNumber;
    @Field
    String callButtonTitle;
    @Field
    String callAlertPositiveButtonTitle;
    @Field
    String callAlertNegativeButtonTitle;
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

    public String getBody() {
        return ActivityUtils.checkNull(body);
    }

    public String getSupportPhoneNumber() {
        return ActivityUtils.checkNull(supportPhoneNumber);
    }

    public String getCallButtonTitle() {
        return ActivityUtils.checkNull(callButtonTitle);
    }

    public String getCallAlertPositiveButtonTitle() {
        return ActivityUtils.checkNull(callAlertPositiveButtonTitle);
    }

    public String getCallAlertNegativeButtonTitle() {
        return ActivityUtils.checkNull(callAlertNegativeButtonTitle);
    }
}
