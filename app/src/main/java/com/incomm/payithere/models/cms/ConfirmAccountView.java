package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;

/**
 * Created by Aashish Godambe on 10/24/17.
 */
@ContentType("confirmAccountView")
public class ConfirmAccountView extends View {
    @Field
    String confirmAccountQuestion;
    @Field
    String confirmAccountButton;
    @Field
    String declineButtonTitle;
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


    public String getConfirmAccountQuestion() {
        return ActivityUtils.checkNull(confirmAccountQuestion);
    }

    public String getConfirmAccountButton() {
        return ActivityUtils.checkNull(confirmAccountButton);
    }

    public String getDeclineButtonTitle() {
        return ActivityUtils.checkNull(declineButtonTitle);
    }
}
