package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;

/**
 * Created by Jerome Davis on 9/12/17.
 */
@ContentType("featureContextualHelp")
public class FeatureContextualHelp extends Feature {

    @Field
    String name;
    @Field
    String key;
    @Field
    String title;
    @Field
    Asset iconPrimary;
    @Field
    Asset iconSecondary;
    @Field
    ContextualHelpView helpViewAndroid;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Asset getIconPrimary() {
        return iconPrimary;
    }

    @Override
    public Asset getIconSecondary() {
        return iconSecondary;
    }

    public ContextualHelpView getHelpViewAndroid() {
        return helpViewAndroid;
    }
}
