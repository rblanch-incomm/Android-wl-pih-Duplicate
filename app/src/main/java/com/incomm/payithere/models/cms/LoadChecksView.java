package com.incomm.payithere.models.cms;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.incomm.payithere.utils.ActivityUtils;


/**
 * Created by Jerome Davis on 3/2/17.
 */
@ContentType("loadChecksView")
public class LoadChecksView extends View {
    @Field
    Asset logoIcon;
    @Field
    String deviceNotSupportedAlertMessage;
    @Field
    String serviceNotAvailableAlertMessage;
    @Field
    String alertPositiveButtonTitle;
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

    public Asset getLogoIcon() {
        return logoIcon;
    }

    public String getDeviceNotSupportedAlertMessage() {
        return ActivityUtils.checkNull(deviceNotSupportedAlertMessage);
    }

    public String serviceNotAvailableAlertMessage() {
        return ActivityUtils.checkNull(serviceNotAvailableAlertMessage);
    }

    public String getAlertPositiveButtonTitle() {
        return ActivityUtils.checkNull(alertPositiveButtonTitle);
    }
}
