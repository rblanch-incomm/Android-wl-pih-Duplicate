package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("reloadLocationsView")
public class ReloadLocationsView extends View {
    @Field
    Asset searchIcon;
    @Field
    String searchPlaceholderText;
    @Field
    String getDirectionsText;
    @Field
    Asset currentLocationIcon;
    @Field
    Asset callIcon;
    @Field
    Asset directionsIcon;
    @Field
    String name;
    @Field
    String title;
    @Field
    String channel;
    @Field
    Double searchRadius;
    @Field
    String analyticsId;

    @Override
    public String getAnalyticsId() {
        return analyticsId;
    }
    public String getGetDirectionsText() {
        return getDirectionsText;
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

    public Asset getSearchIcon() {
        return searchIcon;
    }

    public String getSearchPlaceholderText() {
        return ActivityUtils.checkNull(searchPlaceholderText);
    }

    public String getSearchRadius() {
        return Double.toString(searchRadius);
    }

    public Asset getCurrentLocationIcon() {
        return currentLocationIcon;
    }

    public Asset getCallIcon() {
        return callIcon;
    }

    public Asset getDirectionsIcon() {
        return directionsIcon;
    }
}
