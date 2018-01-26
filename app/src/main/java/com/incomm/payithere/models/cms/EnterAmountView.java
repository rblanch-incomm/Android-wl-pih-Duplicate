package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.incomm.payithere.utils.ActivityUtils;

/**
 * Created by Aashish Godambe on 10/24/17.
 */
@ContentType("enterAmountView")
public class EnterAmountView extends View {
    @Field
    String accountIdLabel;
    @Field
    String enterPaymentPlaceholderText;
    @Field
    String maxPaymentLabelText;
    @Field
    String paymentAmountLabelText;
    @Field
    String feeLabelText;
    @Field
    String totalAmountLabelText;
    @Field
    String dueDatePlaceholderText;
    @Field
    String notificationLabelText;
    @Field
    FeatureContextualHelp contextualHelp;
    @Field
    String createPaymentButtonTitle;
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
    public String getAccountIdLabel() {
        return ActivityUtils.checkNull(accountIdLabel);
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

    public String getEnterPaymentPlaceholderText() {
        return enterPaymentPlaceholderText;
    }

    public String getMaxPaymentLabelText() {
        return maxPaymentLabelText;
    }

    public String getPaymentAmountLabelText() {
        return paymentAmountLabelText;
    }

    public String getFeeLabelText() {
        return feeLabelText;
    }

    public String getTotalAmountLabelText() {
        return totalAmountLabelText;
    }

    public String getDueDatePlaceholderText() {
        return dueDatePlaceholderText;
    }

    public String getNotificationLabelText() {
        return notificationLabelText;
    }

    public FeatureContextualHelp getContextHelpTest() {
        return contextualHelp;
    }

    public String getCreatePaymentButtonTitle() {
        return createPaymentButtonTitle;
    }
}
