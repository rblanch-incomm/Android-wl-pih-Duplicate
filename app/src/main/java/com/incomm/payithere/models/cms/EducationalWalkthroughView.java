package com.incomm.payithere.models.cms;

import com.contentful.vault.ContentType;
import com.contentful.vault.Field;

import java.util.List;

/**
 * Created by Jerome Davis on 10/23/17.
 */
@ContentType("educationalWalkthroughView")
public class EducationalWalkthroughView extends View {

    @Field
    String name;
    @Field
    String channel;
    @Field
    String title;
    @Field
    String skipButtonLabel;
    @Field
    List<EducationalSlide> educationalSlides;
    @Field
    String createPaymentLabel;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getSkipButtonLabel() {
        return skipButtonLabel;
    }

    public String getCreatePaymentLabel() {
        return createPaymentLabel;
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

    public List<EducationalSlide> getEducationalSlides() {
        return educationalSlides;
    }
}
