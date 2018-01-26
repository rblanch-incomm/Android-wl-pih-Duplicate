package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;

import java.util.List;

/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("gettingStartedView")
public class GettingStartedView extends View {

    @Field
    List<Asset> images;
    @Field
    List<Asset> imagesAlternateResolution;
    @Field
    String continueButtonTitle;
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

    public List<Asset> getImages() {
        return images;
    }

    public List<Asset> getImagesAlternateResolution() {
        return imagesAlternateResolution;
    }

    public String getContinueButtonTitle() {
        return ActivityUtils.checkNull(continueButtonTitle);
    }

    public String getAnalyticsId() {
        return AnalyticsId;
    }
}
