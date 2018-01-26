package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;

/**
 * Created by Jerome Davis on 10/23/17.
 */
@ContentType("educationalSlide")
public class EducationalSlide extends View {

    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    String helpText;
    @Field
    Asset image;

    public String getName() {
        return name;
    }

    public String getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }

    public String getHelpText() {
        return helpText;
    }

    public Asset getImage() {
        return image;
    }

}
