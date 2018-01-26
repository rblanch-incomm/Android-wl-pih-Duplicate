package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;

/**
 * Created by Brice on 11/3/17.
 */

@ContentType("enterAccountInformation")
public class EnterAccountInformationView extends View {
    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    String confirmPrefix;
    @Field
    FeatureContextualHelp contextualHelp;
    @Field
    String instructionsText;
    @Field
    String submitButtonTitle;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getChannel() {
        return channel;
    }

    public String getConfirmPrefix() {
        return confirmPrefix;
    }

    public FeatureContextualHelp getContextualHelp() {
        return contextualHelp;
    }

    public String getInstructionsText() {
        return instructionsText;
    }

    public String getSubmitButtonTitle() {
        return submitButtonTitle;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
