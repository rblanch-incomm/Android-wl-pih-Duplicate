package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("forgotPasswordResetView")
public class ForgotPasswordResetView extends View {
    @Field
    String enterPasswordLabelText;
    @Field
    String enterPasswordPlaceholderText;
    @Field
    String confirmPasswordLabelText;
    @Field
    String confirmPasswordPlaceholderText;
    @Field
    String bodyText;
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

    public String getEnterPasswordLabelText() {
        return ActivityUtils.checkNull(enterPasswordLabelText);
    }

    public String getEnterPasswordPlaceholderText() {
        return ActivityUtils.checkNull(enterPasswordPlaceholderText);
    }

    public String getConfirmPasswordLabelText() {
        return ActivityUtils.checkNull(confirmPasswordLabelText);
    }

    public String getConfirmPasswordPlaceholderText() {
        return ActivityUtils.checkNull(confirmPasswordPlaceholderText);
    }

    public String getBodyText() {
        return ActivityUtils.checkNull(bodyText);
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
