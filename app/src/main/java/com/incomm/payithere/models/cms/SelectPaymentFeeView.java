package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;

/**
 * Created by Brice on 11/3/17.
 */

@ContentType("selectBillerFeeView")
public class SelectPaymentFeeView extends View {
    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    String feeLabelText;
    @Field
    String postingTimeLabelText;
    @Field
    FeatureContextualHelp contextualHelp;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getChannel() {
        return channel;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getFeeLabelText() {
        return feeLabelText;
    }

    public String getPostingTimeLabelText() {
        return postingTimeLabelText;
    }

    public FeatureContextualHelp getContextualHelp() {
        return contextualHelp;
    }
}
