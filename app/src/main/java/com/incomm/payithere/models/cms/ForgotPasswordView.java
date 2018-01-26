package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("forgotPasswordUsernameView")
public class ForgotPasswordView extends View {

    @Field
    String bodyText;
    @Field
    String emailPlaceholderText;
    @Field
    String submitButtonTitle;
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

    public String getBodyText() {
        return ActivityUtils.checkNull(bodyText);
    }

    public String getEmailPlaceholderText() {
        return ActivityUtils.checkNull(emailPlaceholderText);
    }

    public String getSubmitButtonTitle() {
        return ActivityUtils.checkNull(submitButtonTitle);
    }

    public String getAnalyticsId() {
        return AnalyticsId;
    }
}
