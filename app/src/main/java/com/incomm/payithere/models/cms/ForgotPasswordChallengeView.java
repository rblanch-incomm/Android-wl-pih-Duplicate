package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/1/17.
 */
@ContentType("forgotPasswordChallengeView")
public class ForgotPasswordChallengeView extends View {
    @Field
    String answerPlaceholderText;
    @Field
    String continueButtonTitle;
    @Field
    String cancelButtonTitle;
    @Field
    FeatureLegalDocument legalDisclaimerFeature;
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

    public String getAnswerPlaceholderText() {
        return ActivityUtils.checkNull(answerPlaceholderText);
    }

    public String getContinueButtonTitle() {
        return ActivityUtils.checkNull(continueButtonTitle);
    }

    public String getCancelButtonTitle() {
        return ActivityUtils.checkNull(cancelButtonTitle);
    }

    public FeatureLegalDocument getLegalDisclaimerFeature() {
        return legalDisclaimerFeature;
    }
}
